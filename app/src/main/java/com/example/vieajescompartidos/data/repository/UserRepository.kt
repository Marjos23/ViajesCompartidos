package com.example.vieajescompartidos.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

data class UserProfile(
    val id: String,
    val userName: String,
    val userEmail: String,
    val initials: String,
    val rating: String,
    val totalTrips: String,
    val pendingTrips: String,
    val isVerified: Boolean
)

interface UserRepository {
    suspend fun getUserProfile(userId: String): Result<UserProfile>
}

class FakeUserRepository : UserRepository {

    private val mockUser = UserProfile(
        id = "1",
        userName = "Valentina Torres",
        userEmail = "valentina.torres@email.com",
        initials = "VT",
        rating = "4.7",
        totalTrips = "12",
        pendingTrips = "3",
        isVerified = true
    )

    override suspend fun getUserProfile(userId: String): Result<UserProfile> = withContext(Dispatchers.IO) {
        delay(600)
        Result.success(mockUser)
    }
}
