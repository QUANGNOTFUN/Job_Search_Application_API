package net.jobsearchapplication_api.routes.user

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import jakarta.validation.Validation
import jakarta.validation.Validator
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.EMPTY_FORM
import net.jobsearchapplication_api.data.repository.user.UserRepository
import net.jobsearchapplication_api.security.UserIdPrincipalForUser
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

fun Application.userRoutes(_repository: UserRepository) {
    routing {
        authenticate {
            route("/user") {
                get("/get-user") {
                    val principal = call.principal<UserIdPrincipalForUser>()

                    val result = _repository.getUser(principal!!.id)
                    call.respond(result.statusCode, result)
                }

                put("/update-info-user") {
                    try {
                        val principal = call.principal<UserIdPrincipalForUser>()
                        val params = call.receive<UpdateInfoUserParams>()

                        val result = _repository.updateInfoUser(principal!!.id, params)
                        call.respond(result.statusCode, result)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}