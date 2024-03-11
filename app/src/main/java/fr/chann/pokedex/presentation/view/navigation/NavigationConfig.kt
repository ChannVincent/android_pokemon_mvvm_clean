package fr.chann.pokedex.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.chann.pokedex.presentation.view.PokemonDetailVIew
import fr.chann.pokedex.presentation.view.PokemonListView

@Composable
fun MyAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "pokemon_list") {
        composable("pokemon_list") {
            PokemonListView(navController)
        }
        composable("pokemon_detail") {
            PokemonDetailVIew(navController)
        }
        // Add more screens as needed
    }
}