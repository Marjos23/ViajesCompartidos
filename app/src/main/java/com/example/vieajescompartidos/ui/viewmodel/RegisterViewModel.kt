package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vieajescompartidos.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegisterUiState(
    val nombres: String = "",
    val apellidos: String = "",
    val cedula: String = "",
    val fechaNacimiento: String = "",
    val telefono: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isRegistrationSuccessful: Boolean = false
)

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNombresChange(nombres: String) { _uiState.update { it.copy(nombres = nombres) } }
    fun onApellidosChange(apellidos: String) { _uiState.update { it.copy(apellidos = apellidos) } }
    fun onCedulaChange(cedula: String) { _uiState.update { it.copy(cedula = cedula) } }
    fun onFechaNacimientoChange(fecha: String) { _uiState.update { it.copy(fechaNacimiento = fecha) } }
    fun onTelefonoChange(telefono: String) { _uiState.update { it.copy(telefono = telefono) } }
    fun onEmailChange(email: String) { _uiState.update { it.copy(email = email) } }
    fun onPasswordChange(password: String) { _uiState.update { it.copy(password = password) } }
    fun onConfirmPasswordChange(confirm: String) { _uiState.update { it.copy(confirmPassword = confirm) } }

    fun register() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val state = _uiState.value
            val result = authRepository.register(
                nombres = state.nombres,
                apellidos = state.apellidos,
                cedula = state.cedula,
                telefono = state.telefono,
                email = state.email,
                password = state.password
            )
            result.onSuccess {
                _uiState.update { it.copy(isLoading = false, isRegistrationSuccessful = true) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }
}
