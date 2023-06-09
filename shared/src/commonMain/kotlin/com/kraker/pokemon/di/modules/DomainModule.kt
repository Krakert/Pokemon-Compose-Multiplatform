package com.kraker.pokemon.di.modules

import com.kraker.pokemon.domain.pokemon.AddFavouritePokemon
import com.kraker.pokemon.domain.pokemon.FetchFavouritePokemons
import com.kraker.pokemon.domain.pokemon.GetFavouriteStorage
import com.kraker.pokemon.domain.pokemon.GetPokemonDetail
import com.kraker.pokemon.domain.pokemon.GetPokemonOverview
import com.kraker.pokemon.domain.pokemon.RemoveFavouritePokemon
import org.koin.dsl.module

val domainModule = module {
    // Overview
    factory { GetPokemonOverview(get()) }
    factory { FetchFavouritePokemons(get()) }

    // Detail
    factory { GetPokemonDetail(get()) }
    factory { AddFavouritePokemon(get()) }
    factory { RemoveFavouritePokemon(get()) }

    // StorageRepository
    factory { GetFavouriteStorage(get()) }
}
