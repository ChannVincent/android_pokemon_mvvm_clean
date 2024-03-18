package fr.chann.pokedex.data.repository

import fr.chann.pokedex.business.FavoritePokemonRepository
import fr.chann.pokedex.data.db.FavoritePokemonDAO
import fr.chann.pokedex.data.db.table.FavoritePokemonTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritePokemonRepositoryDB @Inject constructor(
    private val dao : FavoritePokemonDAO,
) : FavoritePokemonRepository {

    override suspend fun isPokemonFavorite(pokemonId: String): Int {
        return withContext(Dispatchers.IO) { dao.isPokemonFavorite(pokemonId)?.favorite ?: 0 }
    }

    override suspend fun addToFavorite(pokemonId: String, grade: Int) {
        withContext(Dispatchers.IO) { dao.addPokemonToFavorites(FavoritePokemonTable(pokemonId, grade)) }
    }

    override suspend fun removeFromFavorite(pokemonId: String) {
        withContext(Dispatchers.IO) { dao.removePokemonFromFavorites(FavoritePokemonTable(pokemonId)) }
    }

}