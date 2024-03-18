package fr.chann.pokedex.business

import javax.inject.Inject

class AddPokemonToFavoriteUseCase @Inject constructor(
    private val repository: FavoritePokemonRepository,
    private val isPokemonFavoriteUseCase: IsPokemonFavoriteUseCase,
) {
    suspend fun execute(pokemonId: String, grade: Int) {
        if (isPokemonFavoriteUseCase.execute(pokemonId) == 0) {
            repository.addToFavorite(pokemonId, grade)
        }
        else {
            repository.removeFromFavorite(pokemonId)
        }
    }
}