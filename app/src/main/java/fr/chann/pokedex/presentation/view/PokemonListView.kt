package fr.chann.pokedex.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.view.component.ListItemA
import fr.chann.pokedex.presentation.view.component.TextFieldA
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState

@Composable
fun PokemonListView(navController: NavController, viewModel: PokemonListViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(PokemonListEvent.GetAllPokemon)
    }
    val viewState = viewModel.viewState.collectAsState()
    Column {
        TextFieldA(label = "Search your pokemon", onValueChanged = { newValue ->
            viewModel.onEvent(PokemonListEvent.SearchPokemon(newValue))
        })
        Text(text = "LIST VIEW")
        when (val state = viewState.value) {
            is PokemonListViewState.Loading -> Text(text = "Loading")
            is PokemonListViewState.Content -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(
                        items = state.pokemonList,
                        itemContent = {
                            ListItemA(pokemon = it, navController = navController)
                        })
                }
            }
            is PokemonListViewState.Error -> Text(text = "Error")
        }
    }
}