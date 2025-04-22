package net.jobsearchapplication_api.routes.devicetoken

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobsearchapplication_api.data.repository.devicetoken.DeviceTokenRepository
import net.jobsearchapplication_api.data.repository.job.JobRepository

fun Application.DeviceTokenRoutes(repository: DeviceTokenRepository) {
	routing{
		authenticate {
			route("token"){
				get{
					val id = call.request.queryParameters["id"]
					call.respond(repository.getToken(id.toString()))
				}
				post("/createToken"){
					val id = call.request.queryParameters["id"]
					val token = call.request.queryParameters["params"]
					call.respond(repository.createToken(id.toString(), token.toString()))
				}
			}

		}
	}
}