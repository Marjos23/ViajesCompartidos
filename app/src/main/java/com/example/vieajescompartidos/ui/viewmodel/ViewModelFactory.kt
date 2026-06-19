package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vieajescompartidos.data.repository.AuthRepository
import com.example.vieajescompartidos.data.repository.FakeAuthRepository
import com.example.vieajescompartidos.data.repository.FakeTripRepository
import com.example.vieajescompartidos.data.repository.FakeUserRepository
import com.example.vieajescompartidos.data.repository.TripRepository
import com.example.vieajescompartidos.data.repository.UserRepository

class ViewModelFactory : ViewModelProvider.Factory {
    
    companion object {
        private val authRepository: AuthRepository by lazy { FakeAuthRepository() }
        private val tripRepository: TripRepository by lazy { FakeTripRepository() }
        private val userRepository: UserRepository by lazy { FakeUserRepository() }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                HomeViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(PublishTripViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                PublishTripViewModel(tripRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                RegisterViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(SearchResultsViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                SearchResultsViewModel(tripRepository) as T
            }
            modelClass.isAssignableFrom(TripDetailViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                TripDetailViewModel(tripRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                ProfileViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
