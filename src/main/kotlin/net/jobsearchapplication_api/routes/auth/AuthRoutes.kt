package net.jobsearchapplication_api.routes.auth

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.db.schemas.UserRole
import net.jobsearchapplication_api.data.repository.auth.AuthRepository

fun Application.authRoutes(repository: AuthRepository) {
    routing {
        route("/auth") {
//            post("/register") {
//                try {
//                    val params = call.receive<CreateUserParams>()
//                    // Validate role
//                    if (!UserRole.isValid(params.role)) {
//                        call.respond(
//                            HttpStatusCode.BadRequest,
//                            BaseResponse.ErrorResponse("Invalid role. Must be one of: ${UserRole.values().joinToString()}")
//                        )
//                        return@post
//                    }
//
//                    val result = repository.registerUser(params)
//                    call.respond(result.statusCode, result)
//                } catch (e: Exception) {
//                    call.respond(
//                        HttpStatusCode.InternalServerError,
//                        BaseResponse.ErrorResponse(e.message ?: "Registration failed")
//                    )
//                }
//            }
//
//            post("/login") {
//                try {
//                    val params = call.receive<UserLoginParams>()
//                    val result = repository.loginUser(params)
//                    call.respond(result.statusCode, result)
//                } catch (e: Exception) {
//                    call.respond(
//                        HttpStatusCode.InternalServerError,
//                        BaseResponse.ErrorResponse(e.message ?: "Login failed")
//                    )
//                }
//            }

            post("/login-with-google") {
                try {
                    val params = call.receive<LoginWithGoogleParams>()
                    val result = repository.loginWithGoogle(params)
                    call.respond(result.statusCode, result)
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        BaseResponse.ErrorResponse(e.message ?: "Login failed")
                    )
                }
            }
        }
    }
}