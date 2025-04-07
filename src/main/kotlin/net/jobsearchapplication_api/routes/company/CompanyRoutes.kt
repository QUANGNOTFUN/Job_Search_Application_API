package net.jobsearchapplication_api.routes.company

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.repository.company.CompanyRepository
import java.util.UUID

fun Application.companyRoutes(repository: CompanyRepository) {
    routing {
        authenticate {
            route("/company") {
                // GET /companies - Lấy danh sách công ty với phân trang
                get {
                    val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
                    val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
                    val response = repository.getAllCompanies(page, limit)
                    call.respond(response)
                }

                // POST /companies/add - Tạo công ty mới
                post("/add") {
                    val params = call.receive<CompanyParams>()
                    val response = repository.createCompany(params)
                    call.respond(response)
                }

                // GET /companies/get/{id} - Lấy thông tin công ty theo ID
                get("/get/{id}") {
                    val idParam = call.parameters["id"]
                    val id: String = try {
                        idParam
                            ?: return@get call.respond(
                                HttpStatusCode.BadRequest,
                                BaseResponse.ErrorResponse(message = "Invalid company ID")
                            )
                    } catch (e: IllegalArgumentException) {
                        return@get call.respond(
                            HttpStatusCode.BadRequest,
                            BaseResponse.ErrorResponse(message = "Invalid UUID format")
                        )
                    }

                    val response = repository.getCompanyById(id)
                    when (response) {
                        is BaseResponse.SuccessResponse -> call.respond(HttpStatusCode.OK, response)
                        is BaseResponse.ErrorResponse -> call.respond(
                            response.statusCode ?: HttpStatusCode.NotFound,
                            response
                        )
                    }
                }

                // PUT /companies/update/{id} - Cập nhật thông tin công ty
                put("/update/{id}") {
                    val idPrincipal = call.parameters["id"]
                    val params = call.receive<CompanyParams>()
                    val id: String = idPrincipal.toString()

                    val response = repository.updateCompany(id, params)
                    call.respond(response)
                }


                // DELETE /companies/delete/{id} - Xóa công ty
                delete("/delete/{id}") {
                    val idString =
                        call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing ID")
                    val id: String = idString
                    val response = repository.deleteCompany(id)
                    call.respond(response)
                }
                // GET /companies/{id}/jobs - Lấy danh sách công việc của công ty
                get("/jobs/{id}") {
                    val id = call.parameters["id"]
                        ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid company ID")
                    val response = repository.getCompanyJobs(id)
                    call.respond(response)
                }
            }
        }
    }
}
