package net.jobsearchapplication_api.routes.job

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.job.JobRepository
import java.util.*

fun Application.jobRoutes(repository: JobRepository) {
    routing {
//        authenticate {
            route("/jobs") {

                // Lấy danh sách jobs
                get {
                    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
                    call.respond(repository.getAllJobs(page, limit))
                }

                // Lấy danh sách jobs theo user
                get("/posted") {
//                    try {
//                        val currentUser = call.getUser()
//                        val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
//                        val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
//
//                        val response = JobRepository.getJobsByUserId(currentUser.id, page, limit)
//                        call.respond(response)
//                    } catch (e: Exception) {
//                        call.respond(
//                            HttpStatusCode.InternalServerError,
//                            BaseResponse.ErrorResponse("Failed to get jobs")
//                        )
//                    }
                }


                // Tạo job mới
                post("add") {
                    val params = call.receive<JobParams>()
                    val result = repository.createJob(params)
                    call.respond(result.statusCode, result)
                }

                // Chi tiết job
                get("/{id}") {
                    val id = call.parameters["id"]?.let { UUID.fromString(it) }
                    if (id != null) {
                        call.respond(repository.getJobById(id))
                    }
                }

                // Cập nhật job
                put("/{id}") {
                    val id = call.parameters["id"]?.let { UUID.fromString(it) }
                    val params = call.receive<JobParams>()
                    if (id != null) {
                        call.respond(repository.updateJob(id, params))
                    }
                }

                // Xóa job
                delete("/{id}") {
                    val id = call.parameters["id"]?.let { UUID.fromString(it) }
                    if (id != null) {
                        call.respond(repository.deleteJob(id))
                    }
                }

                // Tìm kiếm job
                get("/search") {
                    val query = call.request.queryParameters["q"] ?: ""
                    val location = call.request.queryParameters["location"]
                    val type = call.request.queryParameters["type"]
                    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                    call.respond(repository.searchJobs(query, location, type, page))
                }
//            }
        }
    }
}