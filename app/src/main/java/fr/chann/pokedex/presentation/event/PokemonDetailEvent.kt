package fr.chann.pokedex.presentation.event

sealed class PokemonDetailEvent {

    data class GetPokemon(val pokemonId: String) : PokemonDetailEvent()

}