package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.Pokemon
import fr.chann.pokedex.business.PokemonDetail
import javax.inject.Inject

class PokemonRepositoryAPI @Inject constructor(private val service: PokemonService): PokemonRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        try {
            val result = service.getPokemonList().results
            if (result != null) {
                return result.map { entity ->
                    val id = entity.url.split("/").last()
                    Pokemon(
                        id,
                        entity.name,
                    )
                }
            }
            return emptyList()
        }
        catch (exception : Exception) {
            throw Exception()
        }
    }

    override suspend fun getPokemonDetail(pokemonId: String): PokemonDetail {
        try {
            val entity = service.getPokemon(pokemonId)
            return PokemonDetail(
                entity.id.toString(),
                entity.name,
                entity.weight,
                entity.height,
            )
        }
        catch (exception : Exception) {
            throw Exception()
        }
    }
}