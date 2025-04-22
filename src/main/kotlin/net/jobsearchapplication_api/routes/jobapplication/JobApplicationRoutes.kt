package net.jobsearchapplication_api.routes.jobapplication

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.jobapplication.JobApplicationRepository
import java.io.File
import java.util.*

fun Application.jobApplicationRoutes(repository: JobApplicationRepository) {
    routing {
        route("/applications") {
            post("/apply") {
                try {
                    val multipart = call.receiveMultipart()
                    val paramsMap = mutableMapOf<String, String>()
                    var jobId: UUID? = null
                    var cvFileName: String? = null

                    multipart.forEachPart { part ->
                        try {
                            when (part) {
                                is PartData.FormItem -> {
                                    when (part.name) {
                                        "jobId" -> jobId = try { UUID.fromString(part.value) } catch (e: IllegalArgumentException) { null }
                                        "userId", "status", "coverLetter", "additionalInfo" -> paramsMap[part.name!!] = part.value
                                    }
                                }
                                is PartData.FileItem -> {
                                    if (part.name == "cv") {
                                        val fileExtension = part.originalFileName?.substringAfterLast(".", "") ?: "pdf"
                                        cvFileName = "${UUID.randomUUID()}.$fileExtension"
                                        val file = File("uploads/cvs").apply { mkdirs() }.resolve(cvFileName!!)
                                        part.streamProvider().use { input ->
                                            file.outputStream().buffered().use { output ->
                                                input.copyTo(output)
                                            }
                                        }
                                    }
                                }
                            }
                        } finally {
                            part.dispose()
                        }
                    }

                    if (jobId == null || paramsMap["userId"] == null || cvFileName == null) {
                        call.respond(HttpStatusCode.BadRequest, "Missing required fields or CV file")
                        return@post
                    }

                    val params = JobApplicationParams(
                        jobId = jobId!!,
                        userId = paramsMap["userId"]!!,
                        status = paramsMap["status"] ?: "pending",
                        coverLetter = paramsMap["coverLetter"] ?: "",
                        cvUrl = "/uploads/cvs/$cvFileName",
                        additionalInfo = paramsMap["additionalInfo"] ?: ""
                    )

                    val result = repository.createJobApplication(params)
                    call.respond(result.statusCode, result)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
                }
            }

            get("/getAppliedJobs/{id}") {
                val id = call.parameters["id"]
                if (id != null) {
                    val result = repository.getJobApplicationsByUserId(id)
                    call.respond(result)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Thiếu user ID")
                }
            }
                get("/getAppliedUsers") {
                val userId = call.request.queryParameters["userId"]
                val jobIdStr = call.request.queryParameters["jobId"]

                if (userId != null && jobIdStr != null) {
                    // Chuyển jobId từ String thành UUID
                    val jobId = try {
                        UUID.fromString(jobIdStr)  // Chuyển đổi String thành UUID
                    } catch (e: IllegalArgumentException) {
                        call.respond(HttpStatusCode.BadRequest, "jobId không hợp lệ")
                        return@get
                    }

                    // Gọi phương thức repository với userId và jobId đã chuyển đổi
                    val result = repository.getJobApplicationsByUserIdAndJobId(userId, jobId)
                    call.respond(result)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Thiếu userId hoặc jobId")
                }
            }


        }
    }
}