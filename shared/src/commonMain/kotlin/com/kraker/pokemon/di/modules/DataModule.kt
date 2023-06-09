package com.kraker.pokemon.di.modules

import com.kraker.pokemon.data.components.net.Api
import com.kraker.pokemon.data.components.net.KtorRequest
import com.kraker.pokemon.data.components.net.getHttpClient
import com.kraker.pokemon.data.components.net.mapper.ResponseMapper
import com.kraker.pokemon.data.pokemon.PokemonRepositoryImpl
import com.kraker.pokemon.data.pokemon.mapper.PokemonDetailAbilitiesMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonDetailEvolutionChainMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonDetailMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonDetailStatsMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonDetailTypeMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonFavouriteMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonIdMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonImageMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonOverviewItemMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonOverviewMapper
import com.kraker.pokemon.domain.pokemon.PokemonRepository
import org.koin.dsl.module

val datamodule = module {
    single { getHttpClient() }
    single<Api> { KtorRequest(get()) }
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get(), get(), get(), get()) }
    factory { ResponseMapper() }
    factory { PokemonOverviewMapper(get()) }
    factory { PokemonOverviewItemMapper(get(), get()) }
    factory { PokemonImageMapper() }
    factory { PokemonIdMapper() }
    factory { PokemonFavouriteMapper(get(), get()) }
    factory { PokemonDetailMapper(get(), get(), get(), get()) }
    factory { PokemonDetailTypeMapper() }
    factory { PokemonDetailAbilitiesMapper() }
    factory { PokemonDetailStatsMapper() }
    factory { PokemonDetailEvolutionChainMapper(get(), get()) }
}
