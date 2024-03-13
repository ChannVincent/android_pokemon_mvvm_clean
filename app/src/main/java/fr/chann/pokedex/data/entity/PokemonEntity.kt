package fr.chann.pokedex.data.entity

data class PokemonListResult(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonEntity>?,
)

data class PokemonEntity(
    val name: String,
    val url: String,
)

data class PokemonResult(
    val id: Int,
    val name: String,
    val height: Int,
    val order: Int,
    val weight: Int,
    // todo
)
