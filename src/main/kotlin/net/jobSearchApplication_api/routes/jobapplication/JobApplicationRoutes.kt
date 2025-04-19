package net.jobSearchApplication_api.routes.jobapplication

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.data.repository.jobapplication.JobApplicationRepository

fun Application.jobApplicationRoutes(repository: JobApplicationRepository) {
    routing {
        route("/applications") {
            // Ứng tuyển
            post("/apply"){
                val params = call.receive<JobApplicationParams>()
                val result = repository.createJobApplication(params)
                call.respond(result.statusCode, result)
            }

            get("/getAppliedJobs/{id}") {
                val id = call.parameters["id"]
                if (id != null) {
                    val result = repository.getJobApplicationsByUserId(id)
                    call.respond(result)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Missing user ID")
                }
            }

        }
    }
}