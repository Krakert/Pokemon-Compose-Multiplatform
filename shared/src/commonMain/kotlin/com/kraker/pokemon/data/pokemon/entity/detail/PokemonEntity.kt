package com.kraker.pokemon.data.pokemon.entity.detail

import com.kraker.pokemon.data.pokemon.entity.detail.ability.AbilityEntity
import com.kraker.pokemon.data.pokemon.entity.detail.form.FormEntity
import com.kraker.pokemon.data.pokemon.entity.detail.gameindex.GameIndexEntity
import com.kraker.pokemon.data.pokemon.entity.detail.helditem.HeldItemEntity
import com.kraker.pokemon.data.pokemon.entity.detail.move.MoveEntity
import com.kraker.pokemon.data.pokemon.entity.detail.pasttype.PastTypeEntity
import com.kraker.pokemon.data.pokemon.entity.detail.pasttype.PokemonTypeEntity
import com.kraker.pokemon.data.pokemon.entity.detail.species.PokemonSpeciesEntity
import com.kraker.pokemon.data.pokemon.entity.detail.sprites.PokemonSpritesEntity
import com.kraker.pokemon.data.pokemon.entity.detail.stats.PokemonStatEntity
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEntity(
    val abilities: List<AbilityEntity?>?,
    val base_experience: Int?,
    val forms: List<FormEntity?>?,
    val game_indices: List<GameIndexEntity?>?,
    val height: Int?,
    val held_items: List<HeldItemEntity?>?,
    val id: Int?,
    val is_default: Boolean?,
    val location_area_encounters: String?,
    val moves: List<MoveEntity?>?,
    val name: String?,
    val order: Int?,
    val past_types: List<PastTypeEntity?>?,
    val species: PokemonSpeciesEntity?,
    val sprites: PokemonSpritesEntity?,
    val stats: List<PokemonStatEntity?>?,
    val types: List<PokemonTypeEntity?>?,
    val weight: Int?
)
