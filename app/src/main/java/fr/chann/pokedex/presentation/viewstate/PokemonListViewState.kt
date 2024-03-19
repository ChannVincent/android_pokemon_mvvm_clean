package fr.chann.pokedex.presentation.viewstate

sealed class PokemonListViewState {
    data object Loading : PokemonListViewState()
    data class Content(val pokemonList: List<PokemonCardViewState>) : PokemonListViewState()
    data object Error : PokemonListViewState()
}

sealed class PokemonListViewMode {
    data object Default : PokemonListViewMode()
    data object Search : PokemonListViewMode()
}