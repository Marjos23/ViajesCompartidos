package com.example.vieajescompartidos.data.remote

import com.example.vieajescompartidos.data.remote.model.ProfileDto
import com.example.vieajescompartidos.data.repository.UserProfile
import com.example.vieajescompartidos.data.repository.UserRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SupabaseUserRepository : UserRepository {

    override suspend fun getUserProfile(userId: String): Result<UserProfile> = withContext(Dispatchers.IO) {
        try {
            val profiles = SupabaseProvider.client.postgrest
                .from("profiles")
                .select(Columns.raw("*")) {
                    filter { eq("id", userId) }
                }
                .decodeList<ProfileDto>()

            val profile = profiles.firstOrNull()
                ?: return@withContext Result.failure(Exception("Perfil no encontrado"))

            val (firstName, lastName) = profile.fullName.split(" ", limit = 2).let {
                it.first() to (it.getOrNull(1) ?: "")
            }

            val email = SupabaseProvider.client.auth.currentUserOrNull()?.email ?: ""

            val userProfile = UserProfile(
                id = profile.id,
                userName = profile.fullName,
                userEmail = email,
                initials = "${firstName.firstOrNull() ?: '?'}${lastName.firstOrNull() ?: '?'}",
                rating = "0.0",
                totalTrips = "0",
                pendingTrips = "0",
                isVerified = true
            )
            Result.success(userProfile)
        } catch (e: Exception) {
            Result.failure(Exception("Error al cargar perfil: ${e.message}"))
        }
    }
}
