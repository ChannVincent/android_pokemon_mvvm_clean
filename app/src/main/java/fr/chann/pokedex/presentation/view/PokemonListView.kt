package fr.chann.pokedex.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState

@Composable
fun PokemonListView(navController: NavController, viewModel: PokemonListViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadPokemonList()
    })
    val viewState = viewModel.viewState.collectAsState()
    Column {
        Text(text = "LIST VIEW")
        Button(onClick = { navController.navigate("pokemon_detail") }) {
            Text("Go to Pokemon Detail")
        }
        when (val state = viewState.value) {
            is PokemonListViewState.Loading -> Text(text = "Loading")
            is PokemonListViewState.Content -> {
                state.pokemonList.forEach { pokemon ->
                    Card {
                        Text(text = pokemon.id)
                        Text(text = pokemon.title)
                        Text(text = pokemon.description)
                        Text(text = pokemon.image)
                    }
                }
            }
            is PokemonListViewState.Error -> Text(text = "Error")
        }
    }
}