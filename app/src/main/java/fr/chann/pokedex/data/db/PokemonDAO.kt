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

    @Query("SELECT * FROM PokemonTable WHERE name LIKE '%' || :searchTerm || '%'")
    fun getAllSearchedPokemon(searchTerm: String): List<PokemonTable>

    @Query("SELECT * FROM PokemonTable WHERE id LIKE :pokemonId LIMIT 1")
    fun getPokemon(pokemonId: String) : Flow<PokemonTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PokemonTable>)
}