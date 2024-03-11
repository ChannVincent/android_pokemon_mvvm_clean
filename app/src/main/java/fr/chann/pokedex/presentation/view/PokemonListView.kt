package fr.chann.pokedex.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text


@Composable
fun PokemonListView(navController: NavController) {
    Column {
        Text(text = "LIST VIEW")
        Button(onClick = { navController.navigate("pokemon_detail") }) {
            Text("Go to Pokemon Detail")
        }
    }
}