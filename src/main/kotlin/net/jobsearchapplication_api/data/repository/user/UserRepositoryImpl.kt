package net.jobsearchapplication_api.data.repository.user

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.EMPTY_FORM
import net.jobsearchapplication_api.config.EMPTY_UUID
import net.jobsearchapplication_api.config.SUCCESS_UPDATE_INFO_USER
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.data.service.user.UserService
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getInfoUser(uuid: String): BaseResponse<User> {
        return BaseResponse.SuccessResponse(
            message = "Lấy thông tin thành công",
            data = userService.getUser(uuid)
        )
    }

    override suspend fun updateInfoUser(uuid: String, params: UpdateInfoUserParams): BaseResponse<Any> {
        if (userService.getUser(uuid) == null) {
            return BaseResponse.ErrorResponse(
                message = EMPTY_UUID,
                statusCode = HttpStatusCode.NotFound
            )
        }

        return BaseResponse.SuccessResponse(
            data = userService.updateInfoUser(uuid, params),
            message = SUCCESS_UPDATE_INFO_USER
        )
    }
}