package fr.chann.pokedex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.chann.pokedex.data.db.table.FavoritePokemonTable
import fr.chann.pokedex.data.db.table.PokemonTable
import fr.chann.pokedex.shared.AppConfig

@Database(
    entities = [
        PokemonTable::class,
        FavoritePokemonTable::class],
    version = AppConfig.DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO

    abstract fun favoritePokemonDao(): FavoritePokemonDAO
}