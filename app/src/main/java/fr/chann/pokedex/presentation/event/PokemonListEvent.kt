package fr.chann.pokedex.presentation.event

sealed class PokemonListEvent {

    data object GetAllPokemon : PokemonListEvent()

    data class SearchPokemon(val searchTerm: String) : PokemonListEvent()

}