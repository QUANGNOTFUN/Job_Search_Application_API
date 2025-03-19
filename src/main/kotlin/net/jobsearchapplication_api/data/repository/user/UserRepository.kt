package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse

interface UserRepository {
    suspend fun getUser(id: Int): BaseResponse<Any>
}