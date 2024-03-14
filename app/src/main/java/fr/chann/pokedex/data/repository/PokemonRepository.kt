package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.Pokemon
import fr.chann.pokedex.business.PokemonDetail
import fr.chann.pokedex.data.db.PokemonTable
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    val pokemonList: Flow<List<PokemonTable>>

    suspend fun refreshPokemonList()

    suspend fun getPokemonDetail(pokemonId: String): PokemonDetail
}