package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse
import java.util.*

interface UserRepository {
    suspend fun getUser(id: UUID): BaseResponse<Any>
}