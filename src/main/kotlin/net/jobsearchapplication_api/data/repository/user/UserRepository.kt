package net.jobsearchapplication_api.data.repository.user

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams
import net.jobSearchApplication_api.routes.user.FavoriteParams

interface UserRepository {
    suspend fun getInfoUser(uuid: String): BaseResponse<User>

    suspend fun updateInfoUser(uuid: String, params: UpdateInfoUserParams): BaseResponse<Any>

    suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams): BaseResponse<Any>

}