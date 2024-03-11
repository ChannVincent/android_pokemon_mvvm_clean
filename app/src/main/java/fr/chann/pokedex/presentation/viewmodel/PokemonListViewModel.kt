package fr.chann.pokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel: ViewModel() {

    private val _viewState = MutableStateFlow<PokemonListViewState>(PokemonListViewState.Loading)
    val viewState: StateFlow<PokemonListViewState> = _viewState

    fun updateViewState(newState: PokemonListViewState) {
        viewModelScope.launch {
            _viewState.value = newState
        }
    }

    fun loadPokemonList() {
        // TODO
    }
}

