package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.service.user.UserService

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getUser(id))
    }
}