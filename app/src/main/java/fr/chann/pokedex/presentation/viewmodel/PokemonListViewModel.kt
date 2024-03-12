package fr.chann.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.chann.pokedex.data.repository.PokemonRepository
import fr.chann.pokedex.presentation.viewstate.PokemonCardViewState
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
        private val repository : PokemonRepository,
    ): ViewModel() {

    private val _viewState = MutableStateFlow<PokemonListViewState>(PokemonListViewState.Loading)
    val viewState: StateFlow<PokemonListViewState> = _viewState

    private fun updateViewState(newState: PokemonListViewState) {
        viewModelScope.launch {
            _viewState.value = newState
        }
    }

    fun loadPokemonList() {
        viewModelScope.launch {
            try {
                updateViewState(PokemonListViewState.Loading)
                val pokemonList = repository.getPokemonList()
                updateViewState(PokemonListViewState.Content(pokemonList.map { pokemon ->
                    PokemonCardViewState(
                        pokemon.id,
                        pokemon.name,
                        "",
                        "",
                    )
                }))
            }
            catch (e : Exception) {
                updateViewState(PokemonListViewState.Error)
            }
        }

    }
}

