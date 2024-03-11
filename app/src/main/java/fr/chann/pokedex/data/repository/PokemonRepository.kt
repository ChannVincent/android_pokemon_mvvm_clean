package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.Pokemon

interface PokemonRepository {

    suspend fun getPokemonList() : List<Pokemon>
}