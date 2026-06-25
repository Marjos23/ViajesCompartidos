package com.example.vieajescompartidos.data.remote

import com.example.vieajescompartidos.data.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class SupabaseAuthRepository : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            SupabaseProvider.client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Exception("Error al iniciar sesión: ${e.message}"))
        }
    }

    override suspend fun logout(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            SupabaseProvider.client.auth.signOut()
            Result.success(Unit)
        } catch (e: AuthRestException) {
            Result.failure(Exception("Error al cerrar sesión: ${e.message}"))
        }
    }

    override fun getCurrentUserId(): String? = SupabaseProvider.client.auth.currentUserOrNull()?.id

    override suspend fun register(
        nombres: String,
        apellidos: String,
        cedula: String,
        telefono: String,
        email: String,
        password: String
    ): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            SupabaseProvider.client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
                data = JsonObject(
                    mapOf(
                        "full_name" to JsonPrimitive("$nombres $apellidos"),
                        "phone" to JsonPrimitive(telefono)
                    )
                )
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Exception("Error al registrarse: ${e.message}"))
        }
    }
}
