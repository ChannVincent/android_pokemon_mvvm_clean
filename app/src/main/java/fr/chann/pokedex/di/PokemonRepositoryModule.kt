package fr.chann.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.chann.pokedex.data.repository.PokemonAPIClient
import fr.chann.pokedex.data.repository.PokemonRepository
import fr.chann.pokedex.data.repository.PokemonRepositoryAPI
import fr.chann.pokedex.data.repository.PokemonService

@Module
@InstallIn(SingletonComponent::class)
object PokemonRepositoryModule {

    @Provides
    fun providesPokemonService() : PokemonService {
        return PokemonAPIClient.getService()
    }

    @Provides
    fun providesPokemonRepositoryAPI(service: PokemonService) : PokemonRepositoryAPI {
        return PokemonRepositoryAPI(service)
    }

    @Provides
    fun providesPokemonRepository(pokemonRepositoryAPI: PokemonRepositoryAPI) : PokemonRepository {
        return pokemonRepositoryAPI
    }

}