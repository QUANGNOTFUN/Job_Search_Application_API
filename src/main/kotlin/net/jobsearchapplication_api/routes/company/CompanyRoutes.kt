package net.jobsearchapplication_api.routes.company

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.company.CompanyRepository
import java.util.*
import kotlin.text.get

fun Application.companyRoutes(repository: CompanyRepository) {
    routing {
        authenticate {
            route("/companies") {
                get {
                    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                    call.respond(repository.getAllCompanies(page))
                }

                post {
                    val params = call.receive<CompanyParams>()
                    call.respond(repository.createCompany(params))
                }

                get("/{id}") {
                    val id = call.parameters["id"]?.let { UUID.fromString(it) }
                    if (id != null) {
                        call.respond(repository.getCompanyById(id))
                    }
                }

                get("/{id}/jobs") {
                    val id = call.parameters["id"]?.let { UUID.fromString(it) }
                    if (id != null) {
                        call.respond(repository.getCompanyJobs(id))
                    }
                }
            }
        }
    }
}