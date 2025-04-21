package net.jobsearchapplication_api.data.service.user

import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams
import net.jobsearchapplication_api.routes.user.FavoriteParams

interface UserService {
    suspend fun getUser(uuid: String): User?

    suspend fun updateInfoUser(uuid: String, params: UpdateInfoUserParams)

    suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams)
}