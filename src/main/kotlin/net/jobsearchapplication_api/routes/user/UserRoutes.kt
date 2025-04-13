package net.jobsearchapplication_api.routes.user

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.repository.user.UserRepository
import net.jobsearchapplication_api.security.UserIdPrincipalForUser

fun Application.userRoutes(repository: UserRepository) {
    routing {
//        authenticate {
            route("/user") {
                get("/get-user") {
                    val principal = call.principal<UserIdPrincipalForUser>()

                    val result = repository.getInfoUser(principal!!.id)
                    call.respond(result.statusCode, result)
                }

                get("getInfo") {
                    val query = call.request.queryParameters["uuid"] ?: throw IllegalArgumentException("Không có UUID")

                    val result = repository.getInfoUser(query)
                    call.respond(result)
                }

                put("/updateInfo") {
                    try {
                        val principal = call.principal<UserIdPrincipalForUser>()
                        val params = call.receive<UpdateInfoUserParams>()

                        val result = repository.updateInfoUser(principal!!.id, params)
                        call.respond(result.statusCode, result)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
//        }
    }
}