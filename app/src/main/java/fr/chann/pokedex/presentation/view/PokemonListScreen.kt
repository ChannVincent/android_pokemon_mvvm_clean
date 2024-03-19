package fr.chann.pokedex.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.chann.pokedex.R
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.view.component.GridItem
import fr.chann.pokedex.presentation.view.component.TextField
import fr.chann.pokedex.presentation.view.component.TopBar
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonListViewMode
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState

//
@Composable
fun PokemonListScreen(navController: NavController, viewModel: PokemonListViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(PokemonListEvent.GetAllPokemon)
    }
    val viewState = viewModel.viewState.collectAsState()
    val viewMode = viewModel.viewMode.collectAsState()
    Column {
        TopBar(title = {
                Text(
                    stringResource(R.string.pokedex),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            onSearch = {
                viewModel.onEvent(PokemonListEvent.SwitchSearchMode(
                    searchMode = viewMode.value == PokemonListViewMode.Default
                ))
            }
        )
        when (viewMode.value) {
            PokemonListViewMode.Default -> {
                // display nothing
            }
            PokemonListViewMode.Search -> {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary)
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    TextField(
                        label = stringResource(R.string.search_your_pokemon),
                        onValueChanged = { newValue ->
                        viewModel.onEvent(PokemonListEvent.SearchPokemon(newValue))
                    })
                }
            }
        }
        when (val state = viewState.value) {
            is PokemonListViewState.Loading -> {
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(100.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

            }
            is PokemonListViewState.Content -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    contentPadding = PaddingValues(10.dp),
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
            is PokemonListViewState.Error -> {
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(text = "Internet error")
                    Button(onClick = {
                        viewModel.onEvent(PokemonListEvent.GetAllPokemon)
                    }) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}