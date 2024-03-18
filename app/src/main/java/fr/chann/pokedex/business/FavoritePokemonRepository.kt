package fr.chann.pokedex.business

interface FavoritePokemonRepository {

    suspend fun isPokemonFavorite(pokemonId: String) : Int

    suspend fun addToFavorite(pokemonId: String, grade: Int)

    suspend fun removeFromFavorite(pokemonId: String)

}