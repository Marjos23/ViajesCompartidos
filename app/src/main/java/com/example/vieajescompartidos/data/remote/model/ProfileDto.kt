package com.example.vieajescompartidos.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val id: String,
    @SerialName("full_name") val fullName: String = "",
    val phone: String = "",
    @SerialName("avatar_url") val avatarUrl: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)
