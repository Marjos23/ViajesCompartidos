package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val userName: String = "Valentina",
    val origen: String = "",
    val destino: String = "",
    val recentSearches: List<String> = listOf(
        "📍 Manta → Guayaquil · hace 2 días",
        "📍 Manta → Quito · hace 5 días"
    )
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onOrigenChange(origen: String) {
        _uiState.update { it.copy(origen = origen) }
    }

    fun onDestinoChange(destino: String) {
        _uiState.update { it.copy(destino = destino) }
    }
}
