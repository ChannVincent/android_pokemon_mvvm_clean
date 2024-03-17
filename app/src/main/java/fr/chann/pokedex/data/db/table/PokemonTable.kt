package fr.chann.pokedex.data.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonTable(
    @PrimaryKey val id: String,
    val name: String,
    val icon: String,
    val iconBack: String,
    val image: String,
    val imageShiny: String,
)

// detail
// types
// abilities