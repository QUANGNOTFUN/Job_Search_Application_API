package net.jobsearchapplication_api.data.repository.auth

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.auth.CreateUserParams
import net.jobsearchapplication_api.routes.auth.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams): BaseResponse<Any>
}