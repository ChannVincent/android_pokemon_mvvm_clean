package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.Pokemon

class PokemonRepositoryAPI constructor(private val service: PokemonService): PokemonRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        try {
            return service.getPokemonList().result.map { entity ->
                val id = entity.url.split("/").last()
                Pokemon(
                    id,
                    entity.name,
                )
            }
        }
        catch (exception : Exception) {
            throw Exception()
        }
    }
}