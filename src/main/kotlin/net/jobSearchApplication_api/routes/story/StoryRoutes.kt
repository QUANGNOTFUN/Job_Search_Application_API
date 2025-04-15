package net.jobSearchApplication_api.routes.story

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.data.repository.story.StoryRepository
import net.jobSearchApplication_api.routes.DEFAULT_LIMIT_SIZE
import net.jobSearchApplication_api.routes.DEFAULT_PAGE_START
import net.jobSearchApplication_api.security.UserIdPrincipalForUser

fun Application.storyRoutes(repository: StoryRepository) {
    routing {
        authenticate {
            route("story") {

                get("/my/{page}") {
                    val userId = call.principal<UserIdPrincipalForUser>()?.id
                    if (userId != null) {
                        val isDraft = call.request.queryParameters["is_draft"] == "true"
                        val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                        val page = call.parameters["page"]?.toIntOrNull() ?: DEFAULT_PAGE_START
                        val result = repository.getMyStories(userId, page, limit, isDraft)
                        call.respond(result.statusCode, result)
                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                }

                get("/all/{page?}") {
                    val pageParam = call.parameters["page"]?.toIntOrNull()
                    val page = when {
                        pageParam == null || pageParam < 1 -> 1 // Mặc định trang 1
                        else -> pageParam // Trang do client chỉ định
                    }

                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT_SIZE
                    if (limit <= 0 || limit > 100) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            BaseResponse.ErrorResponse(message = "Limit must be between 1 and 100")
                        )
                        return@get
                    }

                    try {
                        val result = repository.getAllStories(page - 1, limit)
                        call.respond(result.statusCode, result)
                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            BaseResponse.ErrorResponse(message = "Server error: ${e.message}")
                        )
                    }
                }

                post("add") {
                    // Lấy userId từ token để xác thực
                    val userId = call.principal<UserIdPrincipalForUser>()?.id
                    if (userId == null) {
                        call.respond(
                            HttpStatusCode.Unauthorized,
                            BaseResponse.ErrorResponse(message = "User not authenticated")
                        )
                        return@post
                    }

                    // Nhận và xử lý dữ liệu từ body
                    try {
                        val params = call.receive<StoryParams>().copy(userId = userId) // Gán userId từ token
                        val result = repository.add(params)
                        call.respond(result.statusCode, result)
                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            BaseResponse.ErrorResponse(message = "Invalid request body: ${e.message}")
                        )
                    }
                }

                get("/get/{id}") {
                    // Lấy id từ URL
                    val id = call.parameters["id"]?.toIntOrNull()

                    // Kiểm tra id hợp lệ
                    if (id == null || id < 0) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            BaseResponse.ErrorResponse(message = "Invalid story ID")
                        )
                        return@get
                    }

                    // Gọi repository để lấy Story
                    try {
                        val result = repository.get(id)
                        when (result) {
                            is BaseResponse.SuccessResponse -> call.respond(HttpStatusCode.OK, result)
                            is BaseResponse.ErrorResponse -> call.respond(result.statusCode, result)
                        }
                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            BaseResponse.ErrorResponse(message = "Server error: ${e.message}")
                        )
                    }
                }

                put("/update/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull() ?: -1

                    if (id == null || id < 0) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            BaseResponse.ErrorResponse(message = "Invalid story ID")
                        )
                        return@put
                    }
                    val params = call.receive<StoryParams>()
                    val result = repository.update(id, params)
                    call.respond(result.statusCode, result)
                }

                delete("/delete/{id}") {
                    val id = call.parameters["id"]?.toIntOrNull() ?: -1
                    if(id <= 0){
                        call.respond(HttpStatusCode.BadRequest)
                        return@delete
                    }
                    val result = repository.delete(id)
                    call.respond(result.statusCode, result)
                }

                get("{story_id}/comments") {
                    val storyId = call.parameters["story_id"]?.toIntOrNull() ?: -1
                    val result = repository.getComments(storyId)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}