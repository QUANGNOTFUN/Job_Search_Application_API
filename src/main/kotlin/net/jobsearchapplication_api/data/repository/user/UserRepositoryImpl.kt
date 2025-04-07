package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.EMPTY_FORM
import net.jobsearchapplication_api.config.SUCCESS_UPDATE_INFO_USER
import net.jobsearchapplication_api.data.service.user.UserService
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(id: String): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getUser(id))
    }

    override suspend fun updateInfoUser(id: String, params: UpdateInfoUserParams): BaseResponse<Any> {
        return if (userService.updateInfoUser(id, params)) {
            BaseResponse.SuccessResponse(data = null, message = SUCCESS_UPDATE_INFO_USER)
        } else {
            BaseResponse.ErrorResponse(message = EMPTY_FORM)
        }
    }
}