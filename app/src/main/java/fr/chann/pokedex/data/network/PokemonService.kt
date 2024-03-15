package fr.chann.pokedex.data.network

import fr.chann.pokedex.data.entity.PokemonListResult
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList() : PokemonListResult

}