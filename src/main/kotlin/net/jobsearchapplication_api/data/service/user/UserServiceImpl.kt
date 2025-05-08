package net.jobsearchapplication_api.data.service.user

import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.jobsearchapplication_api.routes.user.FavoriteParams
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toUser
import net.jobsearchapplication_api.data.db.schemas.Gender
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class UserServiceImpl : UserService {

    override suspend fun getUser(uuid: String): User? {
        val userRow = dbQuery { UserTable.select { UserTable.id eq uuid }.first() }
        return userRow.toUser()!!
    }

    override suspend fun updateInfoUser(uuid: String, params: UpdateInfoUserParams) {
        dbQuery { UserTable.update({ UserTable.id eq uuid }) { row ->
            params.fullName.takeIf { it.isNotBlank() }?.let { row[fullName] = it }
            params.phoneNumber.takeIf { it.isNotBlank() }?.let { row[phoneNumber] = it }
            params.avatar.takeIf { it.isNotBlank() }?.let { row[avatar] = it }
            params.bio.takeIf { it.isNotBlank() }?.let { row[bio] = it }
            params.birthDay.takeIf { it.isNotBlank() }?.let {
                row[birthDay] = LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
            }
            params.gender.takeIf { it.isNotBlank() }?.let {
                row[gender] = when(it) {
                    "Male" -> Gender.Male
                    "Female" -> Gender.Female
                    else -> Gender.Other
                }
            }
            params.cvUrl.takeIf { it.isNotBlank() }?.let { row[cvUrl] = it }
            row[updatedAt] = LocalDateTime.now()
        }}
    }

    override suspend fun updateImageUser(
        userId: String,
        avatarPart: PartData.FileItem?,
        cvPart: PartData.FileItem?
    ): BaseResponse<Any> {
        // Kiểm tra user tồn tại
        val userResponse = getUser(userId)

        // Đường dẫn lưu file
        val avatarsDir = "uploads/avatars"
        val cvsDir = "uploads/cvs"
        withContext(Dispatchers.IO) {
            Files.createDirectories(Paths.get(avatarsDir))
            Files.createDirectories(Paths.get(cvsDir))
        }

        // Xử lý file avatar
        var newAvatarUrl: String? = userResponse?.avatar
        if (avatarPart != null) {
            // Xóa file cũ nếu có
            if (newAvatarUrl != null) File(newAvatarUrl).delete()

            val fileName = "${UUID.randomUUID()}_${avatarPart.originalFileName}"
            val filePath = "$avatarsDir/$fileName"
            avatarPart.streamProvider().use { input ->
                File(filePath).outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            newAvatarUrl = filePath
            avatarPart.dispose()
        }

        // Xử lý file CV
        var newCvUrl: String? = userResponse?.cvUrl
        if (cvPart != null) {
            // Xóa file cũ nếu có
            if (newCvUrl != null) File(newCvUrl).delete()

            val fileName = "${UUID.randomUUID()}_${cvPart.originalFileName}"
            val filePath = "$cvsDir/$fileName"
            cvPart.streamProvider().use { input ->
                File(filePath).outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            newCvUrl = filePath
            cvPart.dispose()
        }

        // Cập nhật vào database
        return try {
            val updated = dbQuery {
                UserTable.update({ UserTable.id eq userId }) {
                    if (newAvatarUrl != null) it[avatar] = newAvatarUrl
                    if (newCvUrl != null) it[cvUrl] = newCvUrl
                    it[updatedAt] = LocalDateTime.now()
                }
            }
            if (updated == 0) {
                return BaseResponse.ErrorResponse("Không có cập nhật", statusCode = HttpStatusCode.NotFound)
            } else {
                val updatedUser = dbQuery {
                    UserTable.select { UserTable.id eq userId }
                        .map { it.toUser() }
                        .single()
                }
                BaseResponse.SuccessResponse(updatedUser)
            }
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(e.message ?: "Error updating user images", HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams) {
        dbQuery {
            val favoritePosts = UserTable
                .select { UserTable.id eq uuid }.firstOrNull()
                ?.get(UserTable.favoriteJobPosting)
                ?.split(",")?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.toMutableList() ?: mutableListOf()

            if (params.status && !favoritePosts.contains(params.jobId.toString())) {
                params.jobId.takeIf { it.toString().isNotBlank() }?.let { favoritePosts.add(params.jobId.toString()) }
            } else if (!params.status) {
                favoritePosts.remove(params.jobId.toString())
            }

            UserTable.update({ UserTable.id eq uuid }) {
                it[favoriteJobPosting] = favoritePosts.joinToString(",")
            }
        }
    }
}