package net.jobSearchApplication_api.data.service.user

import net.jobSearchApplication_api.data.db.DatabaseFactory.dbQuery
import net.jobSearchApplication_api.data.db.extensions.toUser
import net.jobSearchApplication_api.data.db.schemas.Gender
import net.jobSearchApplication_api.data.db.schemas.UserTable
import net.jobSearchApplication_api.data.models.User
import net.jobSearchApplication_api.routes.user.UpdateInfoUserParams
import net.jobsearchapplication_api.routes.user.FavoriteParams
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    override suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams) {
        dbQuery {
            val favoritePosts = UserTable
                .select { UserTable.id eq uuid }.firstOrNull()
                ?.get(UserTable.favoriteJobPosting)
                ?.split(",")?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.toMutableList() ?: mutableListOf()

            if (params.status) {
                params.jobId.takeIf { it.toString().isNotBlank() }?.let { favoritePosts.add(params.jobId.toString()) }
            } else {
                favoritePosts.remove(params.jobId.toString())
            }

            UserTable.update({ UserTable.id eq uuid }) {
                it[favoriteJobPosting] = favoritePosts.joinToString(",")
            }
        }
    }
}