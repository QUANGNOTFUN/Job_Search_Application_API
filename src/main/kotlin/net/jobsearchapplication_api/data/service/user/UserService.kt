package net.jobsearchapplication_api.data.service.user

import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams

interface UserService {
    suspend fun getUser(uuid: String): User
    suspend fun updateInfoUser(id: String, params: UpdateInfoUserParams): Boolean
}