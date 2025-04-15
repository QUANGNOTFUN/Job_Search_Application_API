package net.jobSearchApplication_api.routes.jobapplication

import io.ktor.application.*
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
    }
    }
}