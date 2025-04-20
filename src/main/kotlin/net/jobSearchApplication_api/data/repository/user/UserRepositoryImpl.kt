package net.jobSearchApplication_api.data.repository.user

import io.ktor.http.*
import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.config.EMPTY_UUID
import net.jobSearchApplication_api.config.SUCCESS_UPDATE_INFO_USER
import net.jobSearchApplication_api.data.models.User
import net.jobSearchApplication_api.data.service.user.UserService
import net.jobSearchApplication_api.routes.user.UpdateInfoUserParams
import net.jobSearchApplication_api.routes.user.FavoriteParams

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

    override suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams): BaseResponse<Any> {
        return try {
            userService.favoriteJobPosting(uuid, params)
            BaseResponse.SuccessResponse(
                data = null,
                message = "Thay đổi yêu thích thành công"
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể yêu thích bài đăng")
        }
    }
}