package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vieajescompartidos.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val userName: String = "",
    val origen: String = "",
    val destino: String = "",
    val recentSearches: List<String> = emptyList(),
    val isLoading: Boolean = false
)

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = userRepository.getUserProfile("1")
            result.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        userName = user.userName.split(" ").first(),
                        recentSearches = listOf(
                            "📍 Manta → Guayaquil · hace 2 días",
                            "📍 Manta → Quito · hace 5 días"
                        ),
                        isLoading = false
                    )
                }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onOrigenChange(origen: String) {
        _uiState.update { it.copy(origen = origen) }
    }

    fun onDestinoChange(destino: String) {
        _uiState.update { it.copy(destino = destino) }
    }
}
