package net.jobsearchapplication_api.routes.job

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.EMPTY_UUID
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

                get("/getJobsByCategory") {
                    try {
                        val cateId = call.request.queryParameters["categoryId"]
                        if(cateId != null) {
                            val result = repository.getJobsOfCategory(cateId.toInt())
                            call.respond(result.statusCode, result)
                        } else {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    } catch (e: Exception) {
                        call.respond(HttpStatusCode.InternalServerError)
                    }
                }

                get("/getFavoriteJobs") {
                    try {
                        val query = call.request.queryParameters["userId"] ?: throw IllegalArgumentException(EMPTY_UUID)
                        val response = repository.getFavoriteJobs(query)
                        call.respond(response.statusCode, response)
                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            BaseResponse.ErrorResponse("Failed to get jobs")
                        )
                    }
                }

                get("/getPostedJobs") {
                    try {
                        val query = call.request.queryParameters["userId"] ?: throw IllegalArgumentException(EMPTY_UUID)
                        val response = repository.getPostedJobs(query)
                        call.respond(response.statusCode, response)
                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            BaseResponse.ErrorResponse("Failed to get jobs")
                        )
                    }
                }

                // Tạo job mới
                post("add") {
                    val params = call.receive<JobParams>()
                    val result = repository.createJob(params)
                    call.respond(result.statusCode, result)
                }

                // Chi tiết job
                get("/getById/{id}") {
                    val id = call.parameters["id"]?.let { UUID.fromString(it) }
                    if (id != null) {
                        call.respond(repository.getJobById(id))
                    }
                }

                put("/update/{id}") {
                    try {
                        // Parse và validate ID
                        val id = call.parameters["id"]?.let {
                            try {
                                UUID.fromString(it)
                            } catch (e: IllegalArgumentException) {
                                null
                            }
                        }

                        if (id == null) {
                            call.respond(
                                HttpStatusCode.BadRequest,
                                BaseResponse.ErrorResponse("Invalid job ID")
                            )
                            return@put
                        }

                        // Parse request body
                        val params = call.receive<JobParams>()

                        // Thực hiện update
                        val response = repository.updateJob(id, params)
                        call.respond(response.statusCode, response)

                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            BaseResponse.ErrorResponse("Failed to update job: ${e.localizedMessage}")
                        )
                    }
                }

                // Xóa job
                delete("/delete/{id}") {
                    val id = call.parameters["id"]?.let { UUID.fromString(it) }
                    if (id != null) {
                        call.respond(repository.deleteJob(id))
                    }
                }

                // Tìm kiếm job -- CHƯA XONG --
				get("/search") {
					val query = call.request.queryParameters["query"] ?: ""
					val location = call.request.queryParameters["location"]
					val type = call.request.queryParameters["type"]
					val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
					if (page < 1) {
						call.respond(HttpStatusCode.BadRequest, "Invalid page number")
						return@get
					}
					call.respond(repository.searchJobs(query, location, type, page))
				}
//            }
        }
    }
}