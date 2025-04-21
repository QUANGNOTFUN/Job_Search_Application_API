package net.jobsearchapplication_api.routes.auth

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.repository.auth.AuthRepository

fun Application.authRoutes(repository: AuthRepository) {
    routing {
        route("/auth") {

            post("/createUser") {
                try {
                    val query = call.request.queryParameters["uuid"]
                    val result = repository.createUser(query.toString())
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