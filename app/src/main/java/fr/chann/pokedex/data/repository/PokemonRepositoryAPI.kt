package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.PokemonRepository
import fr.chann.pokedex.data.db.PokemonDAO
import fr.chann.pokedex.data.db.table.PokemonTable
import fr.chann.pokedex.data.network.PokemonService
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
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/${id}.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/${id}.png"
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

    override suspend fun getPokemon(pokemonId: String): Flow<PokemonTable> {
        return dao.getPokemon(pokemonId)
    }

}