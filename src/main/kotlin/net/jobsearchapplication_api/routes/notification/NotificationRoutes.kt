package net.jobSearchapplication_api.routes.notification

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import net.jobSearchapplication_api.data.repository.notification.NotificationRepositoryImpl
import net.jobsearchapplication_api.base.BaseResponse


fun Application.notificationRoutes(repository: NotificationRepositoryImpl) {
	routing {
		// authenticate {
		route("/notification") {
			get("/all") {
				val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
				val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
				val idParam = call.request.queryParameters["userId"] ?: return@get call.respond(
					HttpStatusCode.BadRequest,
					"Thiếu userId"
				)
				val response = repository.getAllNotificationByUserId(page, limit, idParam)
				call.respond(response)
			}

			post("/add") {
				val params = call.receive<NotificationParams>()
				val response = repository.createNotification(params)
				call.respond(response)
			}

			delete("/delete/{id}") {
				val idString = call.parameters["id"] ?: return@delete call.respond(
					HttpStatusCode.BadRequest,
					"Missing ID"
				)
				val id = try {
					idString.toLong()
				} catch (e: NumberFormatException) {
					return@delete call.respond(
						HttpStatusCode.BadRequest,
						"Invalid ID format, expected a number"
					)
				}
				val response = repository.deleteNotification(id)
				call.respond(response)
			}
			put("/update") {
				val id = call.parameters["id"]?.toLongOrNull()
					?: return@put call.respond(BaseResponse.ErrorResponse(null.toString(), HttpStatusCode.BadRequest))

				val isRead = call.parameters["isRead"]?.toBoolean() ?: false

				val success = repository.updateNotification(id, isRead)
				call.respond(
					BaseResponse.SuccessResponse(success, "Thành công")
				)
			}
		}
	}
}