package net.jobsearchapplication_api.routes.user

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.routes.user.FavoriteParams
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.EMPTY_UUID
import net.jobsearchapplication_api.data.repository.user.UserRepository

fun Application.userRoutes(repository: UserRepository) {
    routing {
//        authenticate {
            route("/user") {
                get("/getInfo") {
                    try {
                        val query = call.request.queryParameters["uuid"]

                        val result = repository.getInfoUser(query.toString())
                        call.respond(result.statusCode, result)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                put("/updateInfo") {
                    try {
                        val query = call.request.queryParameters["uuid"] ?: throw IllegalArgumentException(EMPTY_UUID)
                        val params = call.receive<UpdateInfoUserParams>()

                        val result = repository.updateInfoUser(query, params)
                        call.respond(result.statusCode, result)
                    } catch (e: Exception) {
                        call.respond(HttpStatusCode.BadRequest, message = "Lỗi server: ${e.message} ")
                    }
                }

                put("/favoriteJobs") {
                    try {
                        val query = call.request.queryParameters["uuid"] ?: throw IllegalArgumentException(EMPTY_UUID)
                        val params = call.receive<FavoriteParams>()

                        call.respond(repository.favoriteJobPosting(query, params))

                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            BaseResponse.ErrorResponse("Failed to favorite post: ${e.message}")
                        )
                    }
                }


            }
//        }
    }
}