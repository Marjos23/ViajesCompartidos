package com.example.vieajescompartidos.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vieajescompartidos.data.remote.SupabaseAuthRepository
import com.example.vieajescompartidos.data.remote.SupabaseTripRepository
import com.example.vieajescompartidos.data.remote.SupabaseUserRepository
import com.example.vieajescompartidos.data.repository.AuthRepository
import com.example.vieajescompartidos.data.repository.TripRepository
import com.example.vieajescompartidos.data.repository.UserRepository
import com.example.vieajescompartidos.ui.viewmodel.HomeViewModel
import com.example.vieajescompartidos.ui.viewmodel.LoginViewModel
import com.example.vieajescompartidos.ui.viewmodel.ProfileViewModel
import com.example.vieajescompartidos.ui.viewmodel.PublishTripViewModel
import com.example.vieajescompartidos.ui.viewmodel.RegisterViewModel
import com.example.vieajescompartidos.ui.viewmodel.SearchResultsViewModel
import com.example.vieajescompartidos.ui.viewmodel.TripDetailViewModel

class ViewModelFactory(
    private val tripRepository: TripRepository = SupabaseTripRepository(),
    private val authRepository: AuthRepository = SupabaseAuthRepository(),
    private val userRepository: UserRepository = SupabaseUserRepository()
) : ViewModelProvider.Factory {

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
                ProfileViewModel(userRepository, authRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
