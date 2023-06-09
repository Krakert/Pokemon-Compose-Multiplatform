package com.kraker.pokemon.presentation.detail.mapper

import com.kraker.pokemon.data.components.storage.StorageRepository
import com.kraker.pokemon.domain.pokemon.model.PokemonDetail
import com.kraker.pokemon.presentation.extension.delimiterStringFormatter
import com.kraker.pokemon.presentation.detail.model.PokemonDetailDisplay
import com.kraker.pokemon.presentation.formatter.ExperienceFormatter
import com.kraker.pokemon.presentation.formatter.HeightFormatter
import com.kraker.pokemon.presentation.formatter.IdFormatter
import com.kraker.pokemon.presentation.formatter.WeightFormatter
import com.kraker.pokemon.presentation.extension.capitalize

class PokemonDetailDisplayMapper(
    private val idFormatter: IdFormatter,
    private val badgeMapper: PokemonDetailBadgeDisplayMapper,
    private val aboutMapper: PokemonDetailAboutDisplayMapper,
    private val statsMapper: PokemonDetailStatsDisplayMapper,
    private val evolutionChainDisplayMapper: PokemonDetailEvolutionChainDisplayMapper,
    private val weightFormatter: WeightFormatter,
    private val heightFormatter: HeightFormatter,
    private val baseExperienceFormatter: ExperienceFormatter,
    private val storageRepository: StorageRepository
) {
    fun map(pokemonDetail: PokemonDetail): PokemonDetailDisplay {
        return PokemonDetailDisplay(
            name = aboutMapper.map(header = "Name", text = pokemonDetail.name.capitalize()),
            id = pokemonDetail.id,
            idWithLeadingZeros = aboutMapper.map("ID", idFormatter.intToStringWithLeadingZeros(pokemonDetail.id)),
            badges = pokemonDetail.types.map(badgeMapper::map),
            types = aboutMapper.map("Types", pokemonDetail.types.delimiterStringFormatter(", ", true)),
            height = aboutMapper.map("Height", heightFormatter.format(pokemonDetail.height)),
            weight = aboutMapper.map("Weight", weightFormatter.format(pokemonDetail.weight)),
            base = aboutMapper.map("Base", baseExperienceFormatter.format(pokemonDetail.baseExperience)),
            abilities = aboutMapper.map("Abilities", pokemonDetail.abilities.delimiterStringFormatter(", ", true)),
            imageUrl = pokemonDetail.imageUrl,
            stats = pokemonDetail.stats.map(statsMapper::map),
            evolutions = evolutionChainDisplayMapper.map(pokemonDetail.evolutions),
            isFavourite = storageRepository.isPresent(pokemonDetail.id)
        )
    }
}
