package net.jobSearchApplication_api.data.service.user

import net.jobSearchApplication_api.data.models.User
import net.jobSearchApplication_api.routes.user.UpdateInfoUserParams
import net.jobSearchApplication_api.routes.user.FavoriteParams

interface UserService {
    suspend fun getUser(uuid: String): User?

    suspend fun updateInfoUser(uuid: String, params: UpdateInfoUserParams)

    suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams)
}