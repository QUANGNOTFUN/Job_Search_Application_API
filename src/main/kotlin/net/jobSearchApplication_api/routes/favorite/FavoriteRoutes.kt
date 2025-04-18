 package net.jobSearchApplication_api.routes.favorite

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.config.EMPTY_UUID
import net.jobSearchApplication_api.data.repository.favorite.FavoriteRepository
import net.jobsearchapplication_api.routes.user.FavoriteParams

 fun Application.favoritePostsRoutes(
    repository: FavoriteRepository
) {
    routing {
        route("/favorite") {

        }
    }
}