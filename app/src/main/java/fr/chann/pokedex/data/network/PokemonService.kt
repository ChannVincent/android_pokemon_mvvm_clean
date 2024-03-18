package fr.chann.pokedex.data.network

import fr.chann.pokedex.data.entity.PokemonListResult
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ) : PokemonListResult

}