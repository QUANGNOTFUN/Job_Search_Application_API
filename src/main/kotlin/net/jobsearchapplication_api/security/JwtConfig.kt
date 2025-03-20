package net.jobsearchapplication_api.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.UUID

class JwtConfig private constructor(secret: String){

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun createAccessToken(id: UUID): String = JWT
        .create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(CLAIM, id.toString())
        .sign(algorithm)

    companion object{
        private const val ISSUER = "my-story-app"
        private const val AUDIENCE = "my-story-app"
        const val CLAIM = "id"

        lateinit var instance: JwtConfig
            private set

        fun initialize(secret: String){
            synchronized(this){
                if(!this::instance.isInitialized){
                    instance = JwtConfig(secret)
                }
            }
        }
    }
}