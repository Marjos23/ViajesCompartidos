package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProfileUiState(
    val userName: String = "Valentina Torres",
    val userEmail: String = "valentina.torres@email.com",
    val initials: String = "VT",
    val rating: String = "4.7",
    val totalTrips: String = "12",
    val pendingTrips: String = "3",
    val isVerified: Boolean = true
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
}
