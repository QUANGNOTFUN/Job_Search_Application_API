package net.jobSearchApplication_api.data.service.favorite

import net.jobSearchApplication_api.data.db.DatabaseFactory.dbQuery
import net.jobSearchApplication_api.data.db.extensions.JobWithCompany
import net.jobSearchApplication_api.data.db.schemas.PostTable
import net.jobSearchApplication_api.data.db.schemas.UserTable
import net.jobSearchApplication_api.routes.favorite.FavoriteParams
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime
import java.util.*

class FavoriteServiceImpl : FavoriteService {
    override suspend fun getSavedJobs(userId: String): List<JobWithCompany> {
        TODO("Not yet implemented")
    }

    override suspend fun saveJob(userId: String, jobId: UUID): Boolean {
        return try {
            dbQuery {
                PostTable.insert {
                    it[PostTable.userId] = userId
                    it[PostTable.jobId] = jobId
                    it[savedAt] = LocalDateTime.now()
                }
            }
            true // Trả về true nếu insert thành công
        } catch (e: Exception) {
            false // Trả về false nếu có lỗi
        }
    }

    override suspend fun unSaveJob(userId: String, jobId: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isJobSaved(userId: String, jobId: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedJobCount(userId: String): Int {
        TODO("Not yet implemented")
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