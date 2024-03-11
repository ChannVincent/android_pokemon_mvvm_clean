package fr.chann.pokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState

class PokemonListViewModel: ViewModel() {

    private val _viewState = MutableLiveData<PokemonListViewState>()
    val viewState: LiveData<PokemonListViewState>
        get() = _viewState

    fun loadProductList() {
        // todo
    }
}

