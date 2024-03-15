package fr.chann.pokedex.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.chann.pokedex.presentation.view.theme.PokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    PokedexTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyAppNavHost(navController = navController)
        }
    }
}

@Composable
fun MyAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "pokemon_list") {
        composable("pokemon_list") {
            PokemonListView(navController)
        }
        composable("pokemon_detail/{pokemonId}") {backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: ""
            PokemonDetailVIew(navController, pokemonId = pokemonId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}