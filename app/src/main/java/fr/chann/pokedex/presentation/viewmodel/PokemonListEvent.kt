package fr.chann.pokedex.presentation.viewmodel

sealed class PokemonListEvent {

    data object GetAllPokemon : PokemonListEvent()

}