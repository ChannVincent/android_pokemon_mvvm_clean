package fr.chann.pokedex.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PokemonDetailVIew(navController: NavController) {
    Column {
        Text(text = "DETAIL VIEW")
        Button(onClick = { navController.navigate("pokemon_list") }) {
            Text("Go to Pokemon List")
        }
    }
}