package fr.chann.pokedex.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.chann.pokedex.data.db.AppDatabase
import fr.chann.pokedex.data.db.FavoritePokemonDAO
import fr.chann.pokedex.data.db.PokemonDAO
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "APP_DATABASE"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase): PokemonDAO = appDatabase.pokemonDao()

    @Provides
    @Singleton
    fun provideFavoritePokemonDao(appDatabase: AppDatabase): FavoritePokemonDAO = appDatabase.favoritePokemonDao()
}

