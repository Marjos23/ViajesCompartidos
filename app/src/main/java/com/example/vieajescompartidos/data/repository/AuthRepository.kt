package com.example.vieajescompartidos.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
}

class FakeAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> = withContext(Dispatchers.IO) {
        delay(2000) // Simular retardo de red
        if (email.isNotEmpty() && password.length >= 6) {
            Result.success(true)
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }
}
