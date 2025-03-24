package net.jobsearchapplication_api.data.service.savedjob

import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.JobWithCompany
import net.jobsearchapplication_api.data.db.extensions.toSavedJob
import net.jobsearchapplication_api.data.db.schemas.SavedJobTable
import net.jobsearchapplication_api.data.db.schemas.SavedJobTable.savedAt
import net.jobsearchapplication_api.data.models.SavedJob
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime
import java.util.*

class SavedJobServiceImpl : SavedJobService {
    override suspend fun getSavedJobs(userId: UUID): List<JobWithCompany> {
        TODO("Not yet implemented")
    }

    override suspend fun saveJob(userId: UUID, jobId: UUID): Boolean {
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

    override suspend fun unsaveJob(userId: UUID, jobId: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isJobSaved(userId: UUID, jobId: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedJobCount(userId: UUID): Int {
        TODO("Not yet implemented")
    }

}