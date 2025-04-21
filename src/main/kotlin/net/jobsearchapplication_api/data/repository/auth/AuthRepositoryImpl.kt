package net.jobsearchapplication_api.data.repository.auth

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.*
import net.jobsearchapplication_api.data.service.auth.AuthService

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun createUser(uuid: String): BaseResponse<Any> {
        if (isUuidExist(uuid)) {
            return BaseResponse.SuccessResponse(
                data = null,
                message = "Đăng nhập trở lại"
            )
        }

        return if (authService.createUser(uuid)) {
            BaseResponse.SuccessResponse(
                data = null,
                message = USER_LOGIN_SUCCESS
            )
        } else {
            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE, HttpStatusCode.Unauthorized)
        }
    }

    private suspend fun isUuidExist(uuid: String): Boolean {
        return authService.findUserByUUID(uuid) != null
    }
}