package fr.chann.pokedex.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.chann.pokedex.data.db.table.FavoritePokemonTable

@Dao
interface FavoritePokemonDAO {

    @Query("SELECT * FROM favoritepokemontable WHERE id=:id")
    fun isPokemonFavorite(id: String) : FavoritePokemonTable?

    @Insert
    fun addPokemonToFavorites(pokemon : FavoritePokemonTable)

    @Delete
    fun removePokemonFromFavorites(pokemon : FavoritePokemonTable)
}