package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams

interface UserRepository {
    suspend fun getUser(id: String): BaseResponse<Any>
    suspend fun updateInfoUser(id: String, params: UpdateInfoUserParams): BaseResponse<Any>
}