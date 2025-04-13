package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams

interface UserRepository {
    suspend fun getInfoUser(uuid: String): BaseResponse<User>
    suspend fun updateInfoUser(id: String, params: UpdateInfoUserParams): BaseResponse<Any>
}