package net.jobsearchapplication_api.routes.savedjob

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.repository.savejob.SavedJobRepository
import net.jobsearchapplication_api.routes.job.JobParams
import net.jobsearchapplication_api.utils.getUserId
import java.util.*

fun Application.savedJobRoutes(repository: SavedJobRepository) {
    routing {
        authenticate {
            // Lấy danh sách jobs đã save
            get {
                try {
                    val userId = call.getUserId()
                    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10

                    call.respond(repository.getSavedJobs(userId, page, limit))
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        BaseResponse.ErrorResponse("Failed to get saved jobs")
                    )
                }
            }
            // Save một job
            post("/saved-jobs") {
                try {
                    val userId = call.getUserId()
                    val params = call.receive<SavedJobParams>()

                    // Gọi repository với userId và jobId riêng biệt
                    call.respond(repository.saveJob(userId, params.jobId))
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        BaseResponse.ErrorResponse("Failed to save job")
                    )
                }
            }

            // Unsave một job
            delete("/{jobId}") {
                try {
                    val userId = call.getUserId()
                    val jobId = call.parameters["jobId"]?.let {
                        try {
                            UUID.fromString(it)
                        } catch (e: IllegalArgumentException) {
                            null
                        }
                    }

                    if (jobId == null) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            BaseResponse.ErrorResponse("Invalid job ID")
                        )
                        return@delete
                    }

                    call.respond(repository.unsaveJob(userId, jobId))
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        BaseResponse.ErrorResponse("Failed to unsave job")
                    )
                }
            }

            // Kiểm tra job đã được save chưa
            get("/{jobId}/check") {
                try {
                    val userId = call.getUserId()
                    val jobId = call.parameters["jobId"]?.let {
                        try {
                            UUID.fromString(it)
                        } catch (e: IllegalArgumentException) {
                            null
                        }
                    }

                    if (jobId == null) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            BaseResponse.ErrorResponse("Invalid job ID")
                        )
                        return@get
                    }

                    call.respond(repository.isJobSaved(userId, jobId))
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        BaseResponse.ErrorResponse("Failed to check saved status")
                    )
                }
            }
        }
    }
}