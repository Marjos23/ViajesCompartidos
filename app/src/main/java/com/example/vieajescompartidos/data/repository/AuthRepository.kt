package com.example.vieajescompartidos.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(
        nombres: String,
        apellidos: String,
        cedula: String,
        telefono: String,
        email: String,
        password: String
    ): Result<Boolean>
    suspend fun logout(): Result<Unit>
    fun getCurrentUserId(): String?
}

class FakeAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> = withContext(Dispatchers.IO) {
        delay(2000)
        if (email.isNotEmpty() && password.length >= 6) {
            Result.success(true)
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }

    override suspend fun register(
        nombres: String,
        apellidos: String,
        cedula: String,
        telefono: String,
        email: String,
        password: String
    ): Result<Boolean> = withContext(Dispatchers.IO) {
        delay(2000)
        if (nombres.isNotBlank() && apellidos.isNotBlank() && cedula.length >= 10 && email.isNotEmpty() && password.length >= 6) {
            Result.success(true)
        } else {
            Result.failure(Exception("Datos inválidos. Verifica todos los campos."))
        }
    }

    override suspend fun logout(): Result<Unit> = withContext(Dispatchers.IO) {
        delay(500)
        Result.success(Unit)
    }

    override fun getCurrentUserId(): String? = "1"
}
