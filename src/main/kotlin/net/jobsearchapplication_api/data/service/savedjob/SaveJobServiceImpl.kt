package net.jobsearchapplication_api.data.service.savedjob

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.JobWithCompany
import net.jobsearchapplication_api.data.db.schemas.SavedJobTable
import org.jetbrains.exposed.sql.insert
import java.time.LocalDateTime
import java.util.*

class SavedJobServiceImpl : SavedJobService {
    override suspend fun getSavedJobs(userId: String): List<JobWithCompany> {
        TODO("Not yet implemented")
    }

    override suspend fun saveJob(userId: String, jobId: UUID): Boolean {
        return try {
            dbQuery {
                SavedJobTable.insert {
                    it[SavedJobTable.userId] = userId
                    it[SavedJobTable.jobId] = jobId
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

}