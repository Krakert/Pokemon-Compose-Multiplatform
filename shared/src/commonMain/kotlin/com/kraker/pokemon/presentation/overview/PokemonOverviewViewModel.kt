package com.kraker.pokemon.presentation.overview

import com.kraker.pokemon.domain.pokemon.FetchFavouritePokemons
import com.kraker.pokemon.domain.pokemon.GetFavouriteStorage
import com.kraker.pokemon.domain.pokemon.GetPokemonOverview
import com.kraker.pokemon.presentation.DisplayState
import com.kraker.pokemon.presentation.OnDisplay
import com.kraker.pokemon.presentation.OnError
import com.kraker.pokemon.presentation.OnLoading
import com.kraker.pokemon.presentation.overview.mapper.PokemonOverviewDisplayMapper
import com.kraker.pokemon.presentation.overview.mapper.PokemonOverviewItemDisplayMapper
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewContent
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewDisplay
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewItemDisplay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonOverviewViewModel : ViewModel(), KoinComponent {

    private val getPokemonOverview: GetPokemonOverview by inject()
    private val pokemonOverviewDisplayMapper: PokemonOverviewDisplayMapper by inject()
    private val pokemonOverviewItemDisplayMapper: PokemonOverviewItemDisplayMapper by inject()
    private val getFavouriteStorage: GetFavouriteStorage by inject()
    private val fetchFavouritePokemons: FetchFavouritePokemons by inject()

    private val mutableContentFlow = MutableStateFlow<DisplayState<PokemonOverviewDisplay>>(OnLoading)
    val pokemonOverviewDisplayState: StateFlow<DisplayState<PokemonOverviewDisplay>> = mutableContentFlow

    private val mutableIsLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = mutableIsLoading


    private var currentBatch: Int = 0

    init {
        fetchPokemonOverview(currentBatch)
    }

    fun changeContent(content: PokemonOverviewContent) {
        when (content) {
            PokemonOverviewContent.ALL_POKEMON -> {
                when (mutableContentFlow.value) {
                    is OnDisplay -> (mutableContentFlow.value as OnDisplay<PokemonOverviewDisplay>).display.pokemons =
                        emptyList()

                    else -> { /* no-op */
                    }
                }
                currentBatch = 0
                fetchPokemonOverview(currentBatch)
            }

            PokemonOverviewContent.FAVORITES -> {
                fetchFavoritesOverview()
            }
        }
    }

    fun fetchNextBatch() {
        fetchPokemonOverview(currentBatch + 1)
    }


    private fun fetchPokemonOverview(batch: Int) {
        viewModelScope.launch {
            mutableIsLoading.emit(true)
            getPokemonOverview(batch)
                .onFailure { mutableContentFlow.emit(OnError(it)) }
                .onSuccess {
                    currentBatch = it.batch
                    val mappedResult = pokemonOverviewDisplayMapper.map(it, PokemonOverviewContent.ALL_POKEMON)
                    if (mutableContentFlow.value is OnDisplay) {
                        val currentList =
                            (mutableContentFlow.value as OnDisplay<PokemonOverviewDisplay>).display.pokemons.toMutableList()
                        val combined = currentList.apply {
                            addAll(mappedResult.pokemons)
                        }
                        mutableContentFlow.emit(
                            OnDisplay(mappedResult.copy(pokemons = combined))
                        )
                    } else mutableContentFlow.emit(OnDisplay(mappedResult))
                }
            mutableIsLoading.emit(false)
        }
    }

    private fun fetchFavoritesOverview() {
        viewModelScope.launch {
            getFavouriteStorage().onSuccess { favouritePokemons ->
                println(favouritePokemons)
                val list = arrayListOf<PokemonOverviewItemDisplay>()
                mutableContentFlow.emit(OnLoading)
                if (favouritePokemons.isNotEmpty()) {
                    fetchFavouritePokemons(favouritePokemons).onSuccess { pokemonOverviewItem ->
                        pokemonOverviewItem.forEach { item ->
                            list.add(pokemonOverviewItemDisplayMapper.map(item))
                        }
                        mutableContentFlow.emit(
                            OnDisplay(
                                PokemonOverviewDisplay(
                                    selectedContent = PokemonOverviewContent.FAVORITES, pokemons = list
                                )
                            )
                        )

                    }
                }
            }.onFailure { mutableContentFlow.emit(OnError(it)) }
        }
    }
}
