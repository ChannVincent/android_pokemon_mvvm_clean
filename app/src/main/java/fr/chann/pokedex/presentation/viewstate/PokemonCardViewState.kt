package fr.chann.pokedex.presentation.viewstate

data class PokemonCardViewState(
    val id : String,
    val title : String,
    val description : String,
    val image : String,
    val imageShiny: String,
    val favorite : Int,
)