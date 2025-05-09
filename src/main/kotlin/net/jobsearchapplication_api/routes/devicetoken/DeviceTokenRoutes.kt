package net.jobsearchapplication_api.routes.devicetoken

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.devicetoken.DeviceTokenRepository

fun Application.deviceTokenRoutes(repository: DeviceTokenRepository) {
	routing{
//		authenticate {
			route("/token"){
				get{
					val id = call.request.queryParameters["id"]
					call.respond(repository.getToken(id.toString()))
				}
				post("/createToken") {
					val params = call.receive<DeviceTokenParams>()  // ✅ Nhận body JSON
					val result = repository.createToken(params.id, params)
					call.respond(result)
				}

			}

//		}
	}
}