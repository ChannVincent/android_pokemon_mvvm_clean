package fr.chann.pokedex.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.chann.pokedex.presentation.viewmodel.PokemonDetailViewModel
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel

@Composable
fun PokemonDetailVIew(navController: NavController) {
    val viewModel: PokemonDetailViewModel = viewModel()
    Column {
        Text(text = "DETAIL VIEW")
        Button(onClick = { navController.navigate("pokemon_list") }) {
            Text("Go to Pokemon List")
        }
    }
}