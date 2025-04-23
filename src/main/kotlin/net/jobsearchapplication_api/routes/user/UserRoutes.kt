package net.jobsearchapplication_api.routes.user

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.EMPTY_UUID
import net.jobsearchapplication_api.data.repository.user.UserRepository
import java.io.File

fun Application.userRoutes(repository: UserRepository) {

    routing {
        static("/uploads") {
            files("uploads")
        }

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

                put("/updateImage") {
                    try {
                        val userId = call.request.queryParameters["uuid"]
                            ?: return@put call.respond(
                                HttpStatusCode.BadRequest,
                                BaseResponse.ErrorResponse(message = "Thiếu userId", statusCode = HttpStatusCode.BadRequest)
                            )
                        // Nhận multipart form data
                        val multipart = call.receiveMultipart()

                        var avatarPart: PartData.FileItem? = null
                        var cvPart: PartData.FileItem? = null

                        // Duyệt qua các phần trong multipart
                        multipart.forEachPart { part ->
                            when (part) {
                                is PartData.FileItem -> {
                                    when (part.name) {
                                        "avatar" -> avatarPart = part
                                        "cv" -> cvPart = part
                                    }
                                }
                                else -> part.dispose()
                            }
                        }

                        // Kiểm tra định dạng file (tùy chọn)
                        avatarPart?.let {
                            val fileName = it.originalFileName?.lowercase() ?: ""
                            if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
                                it.dispose()
                                return@put call.respond(HttpStatusCode.BadRequest, "Avatar must be PNG or JPG")
                            }
                        }

                        cvPart?.let {
                            val fileName = it.originalFileName?.lowercase() ?: ""
                            if (!fileName.endsWith(".pdf")) {
                                it.dispose()
                                return@put call.respond(HttpStatusCode.BadRequest, "CV must be PDF")
                            }
                        }

                        // Gọi repository để cập nhật
                        when (val result = repository.updateImageUser(userId, avatarPart, cvPart)) {
                            is BaseResponse.SuccessResponse -> {
                                call.respond(HttpStatusCode.OK, result)
                            }
                            is BaseResponse.ErrorResponse -> {
                                call.respond(result.statusCode, result)
                            }
                        }
                    } catch (e: Exception) {
                        call.respond(HttpStatusCode.InternalServerError, e.message ?: "Internal server error")
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