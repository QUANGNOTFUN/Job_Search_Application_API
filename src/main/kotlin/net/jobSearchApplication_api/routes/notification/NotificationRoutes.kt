package net.jobSearchApplication_api.routes.notification

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.data.repository.notification.NotificationRepositoryImpl
import java.util.*

fun Application.notificationRoutes(repository: NotificationRepositoryImpl){
	routing{
//		authenticate {
			route("/notification"){
				get("/all"){
					val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
					val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
					val response = repository.getAllNotifications(page, limit)
					call.respond(response)
				}
				post("/add") {
					val params = call.receive<NotificationParams>()
					val response = repository.createNotification(params)
					call.respond(response)
				}

				get("/get/{id}") {
					val idParam = call.parameters["id"]
					val id: UUID = try {
						idParam?.let { UUID.fromString(it) }
							?: return@get call.respond(
								HttpStatusCode.BadRequest,
								BaseResponse.ErrorResponse(message = "Invalid NotificationID")
							)
					} catch (e: IllegalArgumentException) {
						return@get call.respond(
							HttpStatusCode.BadRequest,
							BaseResponse.ErrorResponse(message = "Invalid UUID format")
						)
					}

					val response = repository.getNotificationById(id)
					when (response) {
						is BaseResponse.SuccessResponse -> call.respond(HttpStatusCode.OK, response)
						is BaseResponse.ErrorResponse -> call.respond(
							response.statusCode ?: HttpStatusCode.NotFound,
							response
						)
					}
				}

				put("/update/{id}") {
					val idPrincipal = call.parameters["id"]
					val params = call.receive<NotificationParams>()
					val id = try {
						UUID.fromString(idPrincipal)
					} catch (e: IllegalArgumentException) {
						return@put call.respond(HttpStatusCode.BadRequest, "Invalid UUID format")
					}
					val response = repository.updateNotification(id, params)
					call.respond(response)
				}


				delete("/delete/{id}") {
					val idString =
						call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing ID")
					val id = try {
						UUID.fromString(idString)
					} catch (e: IllegalArgumentException) {
						return@delete call.respond(HttpStatusCode.BadRequest, "Invalid UUID format")
					}
					val response = repository.deleteNotification(id)
					call.respond(response)
				}
			}
		}
	}
//}

