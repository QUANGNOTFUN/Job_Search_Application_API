package net.jobSearchApplication_api.routes.jobapplication

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.data.repository.jobapplication.JobApplicationRepository
import java.io.File
import java.util.*

fun Application.jobApplicationRoutes(repository: JobApplicationRepository) {
    routing {
        route("/applications") {
            post("/apply") {
                val multipart = call.receiveMultipart()
                var jobId: UUID? = null
                var userId: String? = null
                var status: String? = null
                var coverLetter: String? = null
                var additionalInfo: String? = null
                var cvFileName: String? = null

                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {
                            when (part.name) {
                                "jobId" -> jobId = part.value.let { UUID.fromString(it) }
                                "userId" -> userId = part.value
                                "status" -> status = part.value
                                "coverLetter" -> coverLetter = part.value
                                "additionalInfo" -> additionalInfo = part.value
                            }
                        }
                        is PartData.FileItem -> {
                            if (part.name == "cv") {
                                val fileExtension = part.originalFileName?.substringAfterLast(".") ?: "pdf"
                                cvFileName = "${UUID.randomUUID()}.$fileExtension"
                                val file = File("uploads/cvs/$cvFileName")
                                file.parentFile.mkdirs()
                                part.streamProvider().use { input ->
                                    file.outputStream().buffered().use { output ->
                                        input.copyTo(output)
                                    }
                                }
                            }
                        }
                        else -> Unit
                    }
                    part.dispose()
                }

                if (jobId == null || userId == null || status == null || coverLetter == null || additionalInfo == null || cvFileName == null) {
                    call.respond(HttpStatusCode.BadRequest, "Thiếu tham số hoặc file CV")
                    return@post
                }

                val params = JobApplicationParams(
                    jobId = jobId!!,
                    userId = userId!!,
                    status = status!!,
                    coverLetter = coverLetter!!,
                    cvUrl = "/uploads/cvs/$cvFileName",
                    additionalInfo = additionalInfo!!
                )

                val result = repository.createJobApplication(params)
                call.respond(result.statusCode, result)
            }

            get("/{id}") {
                val id = call.parameters["id"]
                if (id != null) {
                    val result = repository.getJobApplicationsByUserId(id)
                    call.respond(result)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Thiếu user ID")
                }
            }
        }
    }
}