package com.kraker.pokemon.presentation.overview

import com.kraker.pokemon.domain.pokemon.FetchFavouritePokemons
import com.kraker.pokemon.domain.pokemon.GetFavouriteStorage
import com.kraker.pokemon.domain.pokemon.GetPokemonOverview
import com.kraker.pokemon.presentation.ContentState
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

    private val mutableContentFlow = MutableStateFlow<ContentState<PokemonOverviewDisplay>>(OnLoading)
    val pokemonOverviewContentState: StateFlow<ContentState<PokemonOverviewDisplay>> = mutableContentFlow

    private val mutableIsLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = mutableIsLoading

    private val mutablePageFlow = MutableStateFlow(PokemonOverviewContent.ALL_POKEMON)
    val pokemonOverviewPageState: MutableStateFlow<PokemonOverviewContent> = mutablePageFlow

    private var currentBatch: Int = 0

    init {
        fetchPokemonOverview(currentBatch)
    }

    fun changeContent(content: PokemonOverviewContent) {
        when (content) {
            PokemonOverviewContent.ALL_POKEMON -> {
                mutablePageFlow.value = PokemonOverviewContent.ALL_POKEMON
                mutableContentFlow.value = OnLoading
                currentBatch = 0
            }

            PokemonOverviewContent.FAVORITES -> {
                mutablePageFlow.value = PokemonOverviewContent.FAVORITES
                mutableContentFlow.value = OnLoading
            }
        }
    }

    fun fetchNextBatch() {
        fetchPokemonOverview(currentBatch + 1)
    }

    fun fetchFavourite() {
        fetchFavoritesOverview()
    }

    fun fetchPokemonOverview(batch: Int = currentBatch) {
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
                mutableIsLoading.emit(true)
                val list = arrayListOf<PokemonOverviewItemDisplay>()
                if (favouritePokemons.isNotEmpty()) {
                    fetchFavouritePokemons(favouritePokemons).onSuccess { pokemonOverviewItem ->
                        pokemonOverviewItem.forEach { item ->
                            list.add(pokemonOverviewItemDisplayMapper.map(item))
                        }
                    }
                }
                mutableContentFlow.emit(
                    OnDisplay(
                        PokemonOverviewDisplay(
                            selectedContent = PokemonOverviewContent.FAVORITES, pokemons = list
                        )
                    )
                )
            }.onFailure { mutableContentFlow.emit(OnError(it)) }
        }
    }
}
