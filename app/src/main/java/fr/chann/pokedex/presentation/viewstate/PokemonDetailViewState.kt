package fr.chann.pokedex.presentation.viewstate

sealed class PokemonDetailViewState {
    data object Loading : PokemonDetailViewState()
    data class Content(val pokemon: PokemonViewState) : PokemonDetailViewState()
    data object Error : PokemonDetailViewState()
}

data class PokemonViewState(
    val id : String,
    val title : String,
    val description : String,
    val image : String,
    val imageShiny: String,
    val icon : String,
    val iconBack : String,
    val favorite : Int,
)