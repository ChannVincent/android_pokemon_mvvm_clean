package fr.chann.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.chann.pokedex.business.AddPokemonToFavoriteUseCase
import fr.chann.pokedex.business.FavoritePokemonRepository
import fr.chann.pokedex.business.IsPokemonFavoriteUseCase
import fr.chann.pokedex.data.db.PokemonDAO
import fr.chann.pokedex.data.network.PokemonAPIClient
import fr.chann.pokedex.business.PokemonRepository
import fr.chann.pokedex.business.SearchInPokemonListUseCase
import fr.chann.pokedex.data.db.FavoritePokemonDAO
import fr.chann.pokedex.data.repository.PokemonRepositoryAPI
import fr.chann.pokedex.data.network.PokemonService
import fr.chann.pokedex.data.repository.FavoritePokemonRepositoryDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritePokemonRepositoryModule {

    @Provides
    @Singleton
    fun providesFavoritePokemonRepositoryDB(dao: FavoritePokemonDAO) : FavoritePokemonRepositoryDB {
        return FavoritePokemonRepositoryDB(dao)
    }

    @Provides
    fun providesFavoritePokemonRepository(favoritePokemonRepositoryDB: FavoritePokemonRepositoryDB) : FavoritePokemonRepository {
        return favoritePokemonRepositoryDB
    }

    @Provides
    fun providesIsPokemonFavoriteUseCase(repository: FavoritePokemonRepository) : IsPokemonFavoriteUseCase {
        return IsPokemonFavoriteUseCase(repository)
    }

    @Provides
    fun providesAddPokemonToFavoriteUseCase(
        repository: FavoritePokemonRepository,
        isPokemonFavoriteUseCase: IsPokemonFavoriteUseCase
    ) : AddPokemonToFavoriteUseCase {
        return AddPokemonToFavoriteUseCase(repository, isPokemonFavoriteUseCase)
    }
}