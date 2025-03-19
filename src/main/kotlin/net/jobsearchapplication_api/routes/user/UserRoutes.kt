package net.jobsearchapplication_api.routes.user

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.user.UserRepository
import net.jobsearchapplication_api.security.UserIdPrincipalForUser

fun Application.userRoutes(repository: UserRepository) {
    routing {
        authenticate {
            route("/user") {
                get {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val result = repository.getUser(principal?.id!!)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}