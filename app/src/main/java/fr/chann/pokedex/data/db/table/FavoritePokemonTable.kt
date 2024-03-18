package fr.chann.pokedex.data.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoritePokemonTable(
    @PrimaryKey val id: String,
    val favorite: Int = 0,
)