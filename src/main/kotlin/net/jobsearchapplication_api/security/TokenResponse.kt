package net.jobsearchapplication_api.security

data class TokenResponse(
    val accessToken: String,
    val tokenType: String = "Bearer",
    val expiresIn: Long = 240 * 60 * 60, // 240  giờ tính bằng giây
    val userId: String,
    val name: String,
    val email: String,
    val role: String
)