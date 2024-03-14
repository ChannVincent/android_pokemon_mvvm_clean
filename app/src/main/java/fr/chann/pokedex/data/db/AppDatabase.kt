package fr.chann.pokedex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.chann.pokedex.business.Pokemon

@Database(
    entities = [PokemonTable::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDAO

}