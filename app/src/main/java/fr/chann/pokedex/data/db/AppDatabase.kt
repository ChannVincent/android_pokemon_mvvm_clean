package fr.chann.pokedex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.chann.pokedex.data.db.table.PokemonTable

@Database(
    entities = [PokemonTable::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDAO

}