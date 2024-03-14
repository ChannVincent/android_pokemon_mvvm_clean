package fr.chann.pokedex.data.network

import fr.chann.pokedex.data.entity.PokemonListResult
import fr.chann.pokedex.data.entity.PokemonResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList() : PokemonListResult

    @GET("pokemon/{id}/")
    fun getPokemon(@Path("id") pokemonId: String): PokemonResult
}