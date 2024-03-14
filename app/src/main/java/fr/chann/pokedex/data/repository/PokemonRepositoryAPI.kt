package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.Pokemon
import fr.chann.pokedex.business.PokemonDetail
import fr.chann.pokedex.data.db.PokemonDAO
import fr.chann.pokedex.data.db.PokemonTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryAPI @Inject constructor(
    private val service: PokemonService,
    private val dao: PokemonDAO,
): PokemonRepository {

    override val pokemonList: Flow<List<PokemonTable>> = dao.getAllPokemon()

    override suspend fun refreshPokemonList() {
        try {
            val result = service.getPokemonList().results
            val pokemonList = result?.map { entity ->
                val id = entity.url.split("/").dropLast(1).last()
                PokemonTable(
                    id,
                    entity.name,
                )
            }
            if (pokemonList != null) {
                dao.insertAll(pokemonList)
            }
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