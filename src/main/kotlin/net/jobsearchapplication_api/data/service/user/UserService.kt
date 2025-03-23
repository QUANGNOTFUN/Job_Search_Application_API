package net.jobsearchapplication_api.data.service.user

import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams
import java.util.*

interface UserService {
    suspend fun getUser(id: UUID): User
    suspend fun updateInfoUser(id: UUID, params: UpdateInfoUserParams): Boolean
}