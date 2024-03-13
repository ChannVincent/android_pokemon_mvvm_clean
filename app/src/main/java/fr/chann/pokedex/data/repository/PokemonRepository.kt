package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.Pokemon
import fr.chann.pokedex.business.PokemonDetail

interface PokemonRepository {

    suspend fun getPokemonList() : List<Pokemon>

    suspend fun getPokemonDetail(pokemonId: String): PokemonDetail
}