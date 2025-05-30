package net.jobsearchapplication_api.data.service.user

import io.ktor.http.content.*
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams
import net.jobsearchapplication_api.routes.user.FavoriteParams
import net.jobsearchapplication_api.base.BaseResponse

interface UserService {
    suspend fun getUser(uuid: String): User?

    suspend fun updateInfoUser(uuid: String, params: UpdateInfoUserParams)

    suspend fun updateImageUser(userId: String, avatarPart: PartData.FileItem?, cvPart: PartData.FileItem?): BaseResponse<Any>

    suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams)
}