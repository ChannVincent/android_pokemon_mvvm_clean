package fr.chann.pokedex.data.repository

import fr.chann.pokedex.data.db.table.PokemonTable
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    val pokemonList: Flow<List<PokemonTable>>

    suspend fun refreshPokemonList()

    suspend fun refreshPokemonDetail(pokemonId: String)
}