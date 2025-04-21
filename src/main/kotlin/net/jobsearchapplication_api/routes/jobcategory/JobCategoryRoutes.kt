package net.jobsearchapplication_api.routes.jobcategory

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.jobcategory.JobCategoryRepository

fun Application.jobCategoryRoutes(repository: JobCategoryRepository) {
    routing {
        route("/jobCategories") {
            get("/getAll"){
               val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
               val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 20
                call.respond(repository.getAllCategories(page, limit))
            }

            post {
                val params = call.receive<JobCategoryParams>()

                val category = repository.createCategory(params)
                call.respond(category)
            }

            //Khỏi sửa
            put("/{id}") {
//                val id = call.parameters["id"]?.toIntOrNull()
//                if (id == null) {
//                    call.respond(HttpStatusCode.BadRequest, "Invalid category ID")
//                    return@put
//                }
//                val params = call.receive<JobCategoryParams>()
//                val updated = repository.updateCategory(id, params)
//                call.respond(updated)
            }

            // Rảnh làm tip
            delete("/{id}") {
//                val id = call.parameters["id"]?.toIntOrNull()
//                if (id == null) {
//                    call.respond(HttpStatusCode.BadRequest, "Invalid category ID")
//                    return@delete
//                }
//                val deleted = repository.deleteCategory(id)
//                call.respond(deleted)
            }
        }
    }
}