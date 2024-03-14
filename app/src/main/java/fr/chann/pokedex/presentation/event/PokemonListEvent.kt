package fr.chann.pokedex.presentation.event

sealed class PokemonListEvent {

    data object GetAllPokemon : PokemonListEvent()

}