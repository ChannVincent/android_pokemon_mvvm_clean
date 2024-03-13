package fr.chann.pokedex.business

data class Pokemon(
    val id: String,
    val name: String,
)

data class PokemonDetail(
    val id: String,
    val name: String,
    val height: Int,
    val weight: Int,
)
