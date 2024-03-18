package fr.chann.pokedex.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.view.component.GridItem
import fr.chann.pokedex.presentation.view.component.TextField
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState

//
@Composable
fun PokemonListScreen(navController: NavController, viewModel: PokemonListViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(PokemonListEvent.GetAllPokemon)
    }
    val viewState = viewModel.viewState.collectAsState()
    Column {
        TextField(label = "Search your pokemon", onValueChanged = { newValue ->
            viewModel.onEvent(PokemonListEvent.SearchPokemon(newValue))
        })
        when (val state = viewState.value) {
            is PokemonListViewState.Loading -> Text(text = "Loading")
            is PokemonListViewState.Content -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(state.pokemonList.size) { pokemonIndex ->
                        GridItem(
                            state.pokemonList[pokemonIndex],
                            onImageClick = { pokemonId ->
                                navController.navigate("pokemon_detail/${pokemonId}")
                            },
                            onCrossClick = { pokemonId ->
                                viewModel.onEvent(PokemonListEvent.AddPokemonToFavorite(pokemonId, -1))
                            },
                            onFavoriteClick = { pokemonId ->
                                viewModel.onEvent(PokemonListEvent.AddPokemonToFavorite(pokemonId, 1))
                            }
                        )
                    }
                }
            }
            is PokemonListViewState.Error -> Text(text = "Error")
        }
    }
}