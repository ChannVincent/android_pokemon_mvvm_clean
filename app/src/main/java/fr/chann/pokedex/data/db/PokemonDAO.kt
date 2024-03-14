package fr.chann.pokedex.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.chann.pokedex.data.db.table.PokemonTable
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM PokemonTable")
    fun getAllPokemon(): Flow<List<PokemonTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PokemonTable>)
}