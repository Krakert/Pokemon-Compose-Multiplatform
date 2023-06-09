package com.kraker.pokemon.di.modules

import com.kraker.pokemon.presentation.detail.mapper.PokemonDetailEvolutionChainDisplayMapper
import com.kraker.pokemon.presentation.detail.mapper.PokemonDetailStatsDisplayMapper
import com.kraker.pokemon.presentation.detail.mapper.PokemonDetailAboutDisplayMapper
import com.kraker.pokemon.presentation.detail.mapper.PokemonDetailBadgeDisplayMapper
import com.kraker.pokemon.presentation.detail.mapper.PokemonDetailDisplayMapper
import com.kraker.pokemon.presentation.formatter.ExperienceFormatter
import com.kraker.pokemon.presentation.formatter.HeightFormatter
import com.kraker.pokemon.presentation.formatter.IdFormatter
import com.kraker.pokemon.presentation.formatter.WeightFormatter
import com.kraker.pokemon.presentation.overview.mapper.PokemonOverviewDisplayMapper
import com.kraker.pokemon.presentation.overview.mapper.PokemonOverviewItemDisplayMapper
import org.koin.dsl.module

val presentationModule = module {
    // Overview
    factory { PokemonOverviewDisplayMapper(get()) }
    factory { PokemonOverviewItemDisplayMapper(get()) }

    // Detail
    factory { PokemonDetailDisplayMapper(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    factory { PokemonDetailAboutDisplayMapper() }
    factory { PokemonDetailBadgeDisplayMapper() }
    factory { PokemonDetailStatsDisplayMapper() }
    factory { PokemonDetailEvolutionChainDisplayMapper(get()) }
    factory { WeightFormatter() }
    factory { HeightFormatter() }
    factory { ExperienceFormatter() }

    // used by both
    factory { IdFormatter() }
}
