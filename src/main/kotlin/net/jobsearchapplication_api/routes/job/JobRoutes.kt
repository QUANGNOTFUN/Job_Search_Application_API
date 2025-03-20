package net.jobsearchapplication_api.routes.job

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.job.JobRepository
import java.util.*
import kotlin.text.get

fun Application.jobRoutes(repository: JobRepository) {
    routing {
        authenticate {
            route("/jobs") {
                // Lấy danh sách jobs
                get {
                    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
                    call.respond(repository.getAllJobs(page, limit))
                }

                // Tạo job mới
                post {
                    val params = call.receive<JobParams>()
                    call.respond(repository.createJob(params))
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
            }
        }
    }
}