package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vieajescompartidos.data.repository.AuthRepository
import com.example.vieajescompartidos.data.repository.FakeAuthRepository

class ViewModelFactory : ViewModelProvider.Factory {
    private val authRepository: AuthRepository = FakeAuthRepository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                HomeViewModel() as T
            }
            modelClass.isAssignableFrom(PublishTripViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                PublishTripViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
