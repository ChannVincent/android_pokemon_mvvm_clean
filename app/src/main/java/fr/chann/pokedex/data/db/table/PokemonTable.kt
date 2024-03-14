package fr.chann.pokedex.data.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonTable(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
)

@Entity
data class PokemonDetailTable(
    @PrimaryKey val id: String,
    val pokemonId: String,
    // TODO more info
)