package fr.chann.pokedex.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonAPIClient {

    companion object {
        fun getService() : PokemonService {
            return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PokemonService::class.java)
        }
    }
}