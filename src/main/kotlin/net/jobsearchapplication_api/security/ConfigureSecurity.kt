package net.jobsearchapplication_api.security

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.response.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.INVALID_AUTHENTICATION_TOKEN
import java.util.*

fun Application.configureSecurity() {
    JwtConfig.initialize("4968110512")

    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate { credential ->
                try {
                    val userId = credential.payload.subject?.let { UUID.fromString(it) }
                    val name = credential.payload.getClaim(JwtConfig.CLAIM_NAME).asString()
                    val email = credential.payload.getClaim(JwtConfig.CLAIM_EMAIL).asString()
                    val role = credential.payload.getClaim(JwtConfig.CLAIM_ROLE).asString()

                    if (userId != null && name != null && email != null && role != null) {
                        UserIdPrincipalForUser(
                            id = userId,
                            name = name,
                            email = email,
                            role = role
                        )
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = BaseResponse.ErrorResponse(INVALID_AUTHENTICATION_TOKEN)
                )
            }
        }
    }
}