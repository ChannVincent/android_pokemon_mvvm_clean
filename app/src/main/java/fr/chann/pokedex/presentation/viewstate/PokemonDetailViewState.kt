package fr.chann.pokedex.presentation.viewstate

sealed class PokemonDetailViewState {
    data object Loading : PokemonDetailViewState()
    data class Content(val pokemon: PokemonCardViewState) : PokemonDetailViewState()
    data object Error : PokemonDetailViewState()
}