package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vieajescompartidos.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileUiState(
    val userName: String = "",
    val userEmail: String = "",
    val initials: String = "",
    val rating: String = "",
    val totalTrips: String = "",
    val pendingTrips: String = "",
    val isVerified: Boolean = false,
    val isLoading: Boolean = false
)

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = userRepository.getUserProfile("1")
            result.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        userName = user.userName,
                        userEmail = user.userEmail,
                        initials = user.initials,
                        rating = user.rating,
                        totalTrips = user.totalTrips,
                        pendingTrips = user.pendingTrips,
                        isVerified = user.isVerified,
                        isLoading = false
                    )
                }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
