package net.jobSearchApplication_api.routes.notification

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.data.repository.notification.NotificationRepositoryImpl
import java.util.*

fun Application.notificationRoutes(repository: NotificationRepositoryImpl){
	routing{
//		authenticate {
			route("/notification"){
				get("/all") {
					val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
					val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10
					val idParam = call.request.queryParameters["userId"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing userId")
					val response = repository.getAllNotificationByUserId(page, limit, idParam)
					call.respond(response)
				}

				post("/add") {
					val params = call.receive<NotificationParams>()
					val response = repository.createNotification(params)
					call.respond(response)
				}

				delete("/delete/{id}") {
					val idString = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing ID")
					val id = try {
						UUID.fromString(idString)
					} catch (e: IllegalArgumentException) {
						return@delete call.respond(HttpStatusCode.BadRequest, "Invalid UUID format")
					}
					val response = repository.deleteNotification(id.toString())
					call.respond(response)
				}
			}
		}
	}
//}

