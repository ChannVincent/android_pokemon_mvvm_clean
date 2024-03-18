package fr.chann.pokedex.data.repository

import android.util.Log
import fr.chann.pokedex.business.PokemonRepository
import fr.chann.pokedex.data.db.PokemonDAO
import fr.chann.pokedex.data.db.table.PokemonTable
import fr.chann.pokedex.data.network.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryAPI @Inject constructor(
    private val service: PokemonService,
    private val dao: PokemonDAO,
): PokemonRepository {

    override val pokemonList: Flow<List<PokemonTable>> = dao.getAllPokemon()
    private val _searchedPokemonList = MutableStateFlow<List<PokemonTable>>(emptyList())
    override val searchedPokemonList: StateFlow<List<PokemonTable>> = _searchedPokemonList


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
                withContext(Dispatchers.IO) { dao.insertAll(pokemonList) }
            }
        }
        catch (exception : Exception) {
            throw Exception()
        }
    }

    override suspend fun getPokemon(pokemonId: String): Flow<PokemonTable> {
        return withContext(Dispatchers.IO) { dao.getPokemon(pokemonId) }
    }

    override suspend fun refreshSearchPokemonList(searchTerm: String) {
        withContext(Dispatchers.IO) {
            val result = dao.getAllSearchedPokemon(searchTerm)
            _searchedPokemonList.value = result
        }
    }

}