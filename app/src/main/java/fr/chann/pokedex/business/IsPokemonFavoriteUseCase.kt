package fr.chann.pokedex.business

import javax.inject.Inject

class IsPokemonFavoriteUseCase @Inject constructor(
    private val repository: FavoritePokemonRepository,
) {
    suspend fun execute(pokemonId: String) : Int {
        return repository.isPokemonFavorite(pokemonId)
    }
}