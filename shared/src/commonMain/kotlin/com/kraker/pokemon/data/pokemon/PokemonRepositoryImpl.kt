package com.kraker.pokemon.data.pokemon

import com.kraker.pokemon.data.components.net.Api
import com.kraker.pokemon.data.components.net.mapper.ResponseMapper
import com.kraker.pokemon.data.extension.guard
import com.kraker.pokemon.data.pokemon.api.PokemonApi
import com.kraker.pokemon.data.pokemon.mapper.PokemonDetailMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonFavouriteMapper
import com.kraker.pokemon.data.pokemon.mapper.PokemonOverviewMapper
import com.kraker.pokemon.domain.pokemon.PokemonRepository
import com.kraker.pokemon.domain.pokemon.model.PokemonDetail
import com.kraker.pokemon.domain.pokemon.model.PokemonOverview
import com.kraker.pokemon.domain.pokemon.model.PokemonOverviewItem

class PokemonRepositoryImpl(
    private val ktor: Api,
    private val responseMapper: ResponseMapper,
    private val overviewMapper: PokemonOverviewMapper,
    private val pokemonFavouriteMapper: PokemonFavouriteMapper,
    private val detailMapper: PokemonDetailMapper
) : PokemonRepository {

    companion object {
        private const val BATCH_SIZE = 20
    }

    override suspend fun getOverview(batch: Int): Result<PokemonOverview> {
        val response = Result.runCatching {
            ktor.request(PokemonApi.getAllPokemon(BATCH_SIZE, batch * BATCH_SIZE))
        }.guard { return it }
        val entity = responseMapper.map(response)
        return entity.mapCatching { overviewMapper.map(it, batch) }
    }

    override suspend fun getDetails(id: Int): Result<PokemonDetail> {
        val responseDetails = Result.runCatching {
            ktor.request(PokemonApi.getDetails(id))
        }.guard { return it }

        val evolutionChainUrl = responseMapper.map(ktor.request(PokemonApi.getSpecies(id))).getOrNull()

        val pokemonEvolutionChainEntity = responseMapper.map(
            ktor.requestNoBaseURL(PokemonApi.getEvolutions(chainUrl = evolutionChainUrl?.evolution_chain?.url.toString()))
        ).getOrNull()

        val entity = responseMapper.map(responseDetails)
        return entity.mapCatching { detailMapper.map(it, pokemonEvolutionChainEntity) }
    }

    override suspend fun getFavouritePokemons(ids: List<Int>): Result<List<PokemonOverviewItem>> {
        val list = arrayListOf<PokemonOverviewItem>()
            ids.forEach { id ->
                val response = ktor.request(PokemonApi.getPokemon(id))
                val entity = responseMapper.map(response)
                val mapped = entity.mapCatching { pokemonFavouriteMapper.map(it.results?.get(0)) }
                    mapped.onSuccess { list.add(it) }
            }
        return Result.success(list)
    }
}
