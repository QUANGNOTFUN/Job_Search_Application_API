 package net.jobSearchApplication_api.routes.favorite

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.config.EMPTY_UUID
import net.jobSearchApplication_api.data.repository.favorite.FavoriteRepository
import net.jobSearchApplication_api.utils.getUserId
import java.util.*

fun Application.favoritePostsRoutes(
    repository: FavoriteRepository
) {
    routing {
        route("/favorite") {
            put("/jobPosting") {
                try {
                    val query = call.request.queryParameters["uuid"] ?: throw IllegalArgumentException(EMPTY_UUID)
                    val params = call.receive<FavoriteParams>()

                    call.respond(repository.favoriteJobPosting(query, params))

                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        BaseResponse.ErrorResponse("Failed to favorite post: ${e.message}")
                    )
                }
            }
        }
    }
}