package net.jobSearchApplication_api.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtConfig private constructor(secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    private val accessTokenExpiration = 240 * 60 * 60 * 1000L // 240 giờ

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .build()

    fun createAccessToken(
        id: UUID,
        name: String,
        email: String,
        role: String
    ): String = JWT
        .create()
        .withSubject(id.toString())
        .withClaim(CLAIM_NAME, name)
        .withClaim(CLAIM_EMAIL, email)
        .withClaim(CLAIM_ROLE, role)
        .withIssuedAt(Date())
        .withExpiresAt(Date(System.currentTimeMillis() + accessTokenExpiration))
        .withJWTId(UUID.randomUUID().toString()) // jti for preventing replay attacks
        .sign(algorithm)

    companion object {
        const val CLAIM_NAME = "name"
        const val CLAIM_EMAIL = "email"
        const val CLAIM_ROLE = "role"

        lateinit var instance: JwtConfig
            private set

        fun initialize(secret: String) {
            synchronized(this) {
                if (!this::instance.isInitialized) {
                    instance = JwtConfig(secret)
                }
            }
        }
    }
}