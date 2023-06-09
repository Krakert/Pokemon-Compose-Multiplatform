package com.kraker.pokemon.presentation.detail

import com.kraker.pokemon.domain.pokemon.AddFavouritePokemon
import com.kraker.pokemon.domain.pokemon.GetPokemonDetail
import com.kraker.pokemon.domain.pokemon.RemoveFavouritePokemon
import com.kraker.pokemon.presentation.DisplayState
import com.kraker.pokemon.presentation.OnDisplay
import com.kraker.pokemon.presentation.OnError
import com.kraker.pokemon.presentation.OnLoading
import com.kraker.pokemon.presentation.detail.mapper.PokemonDetailDisplayMapper
import com.kraker.pokemon.presentation.detail.model.PokemonDetailDisplay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonDetailViewModel: ViewModel(), KoinComponent {

    private val pokemonDetailDisplayMapper: PokemonDetailDisplayMapper by inject()
    private val getPokemonDetail: GetPokemonDetail by inject()
        private val addFavouritePokemon: AddFavouritePokemon by inject()
        private val removeFavouritePokemon: RemoveFavouritePokemon by inject()

    private val mutableContent = MutableStateFlow<DisplayState<PokemonDetailDisplay>>(OnLoading)
    val pokemonDetailDisplayState: StateFlow<DisplayState<PokemonDetailDisplay>> = mutableContent

    fun fetchDetails(id: Int) {
        viewModelScope.launch {
            getPokemonDetail(id)
                .onFailure { mutableContent.emit(OnError(it)) }
                .onSuccess {
                    mutableContent.emit(OnDisplay(pokemonDetailDisplayMapper.map(it)))
                }
        }
    }

    fun addPokemonToFavourites(id: Int){
        viewModelScope.launch {
            addFavouritePokemon(id)
        }
    }

    fun removePokemonFromFavourites(id: Int) {
        viewModelScope.launch {
            removeFavouritePokemon(id)
        }
    }
}
