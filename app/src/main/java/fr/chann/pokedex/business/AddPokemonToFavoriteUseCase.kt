package fr.chann.pokedex.business

import javax.inject.Inject

class AddPokemonToFavoriteUseCase @Inject constructor(
    private val repository: FavoritePokemonRepository,
    private val isPokemonFavoriteUseCase: IsPokemonFavoriteUseCase,
) {
    suspend fun execute(pokemonId: String, grade: Int) {
        val favoritePokemon = isPokemonFavoriteUseCase.execute(pokemonId)
        if (favoritePokemon == 0) {
            repository.addToFavorite(pokemonId, grade)
        }
        else if (isPokemonFavoriteUseCase.execute(pokemonId) != 0 && favoritePokemon == grade) {
            repository.removeFromFavorite(pokemonId)
        }
        else {
            repository.removeFromFavorite(pokemonId)
            repository.addToFavorite(pokemonId, grade)
        }
    }
}