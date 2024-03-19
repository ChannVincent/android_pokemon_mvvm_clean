package fr.chann.pokedex.presentation.event

sealed class PokemonListEvent {

    data object GetAllPokemon : PokemonListEvent()

    data class SearchPokemon(val searchTerm: String) : PokemonListEvent()

    data class AddPokemonToFavorite(val pokemonId: String, val grade: Int) : PokemonListEvent()

    data class SwitchSearchMode(val searchMode: Boolean) : PokemonListEvent()
}