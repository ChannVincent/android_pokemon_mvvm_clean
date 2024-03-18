package fr.chann.pokedex.business

import javax.inject.Inject

class SearchInPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend fun execute(searchTerm: String) {
        repository.refreshSearchPokemonList(searchTerm)
    }
}