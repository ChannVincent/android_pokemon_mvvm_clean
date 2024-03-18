package fr.chann.pokedex.business

import fr.chann.pokedex.data.db.table.PokemonTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PokemonRepository {

    val pokemonList: Flow<List<PokemonTable>>

    val searchedPokemonList: StateFlow<List<PokemonTable>>

    suspend fun refreshPokemonList()

    suspend fun getPokemon(pokemonId: String) : Flow<PokemonTable>

    suspend fun refreshSearchPokemonList(searchTerm: String)

}