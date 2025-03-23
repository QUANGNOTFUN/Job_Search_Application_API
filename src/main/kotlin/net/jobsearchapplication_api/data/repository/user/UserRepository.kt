package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams
import java.util.*

interface UserRepository {
    suspend fun getUser(id: UUID): BaseResponse<Any>
    suspend fun updateInfoUser(id: UUID, params: UpdateInfoUserParams): BaseResponse<Any>
}