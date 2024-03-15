package fr.chann.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.chann.pokedex.business.Pokemon
import fr.chann.pokedex.data.repository.PokemonRepository
import fr.chann.pokedex.presentation.event.PokemonDetailEvent
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.viewstate.PokemonCardViewState
import fr.chann.pokedex.presentation.viewstate.PokemonDetailViewState
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository : PokemonRepository,
): ViewModel() {

    private val _viewState = MutableStateFlow<PokemonDetailViewState>(PokemonDetailViewState.Loading)
    val viewState: StateFlow<PokemonDetailViewState> = _viewState

    private fun updateViewState(newState: PokemonDetailViewState) {
        viewModelScope.launch {
            _viewState.value = newState
        }
    }

    fun onEvent(events: PokemonDetailEvent) {
        when (events) {
            is PokemonDetailEvent.GetPokemon -> {
                viewModelScope.launch {
                    getPokemon(events.pokemonId)
                }
            }
        }
    }

    private suspend fun getPokemon(pokemonId: String) {
        repository.getPokemon(pokemonId).collect { pokemonTable ->
            updateViewState(PokemonDetailViewState.Content(
                    PokemonCardViewState(
                        pokemonTable.id,
                        pokemonTable.name,
                        "",
                        pokemonTable.image
                    )
            ))
        }
    }

}
