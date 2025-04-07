package net.jobsearchapplication_api.data.service.savedjob

import net.jobsearchapplication_api.data.db.extensions.JobWithCompany
import java.util.*

interface SavedJobService {
    suspend fun getSavedJobs(userId: String): List<JobWithCompany>
    suspend fun saveJob(userId: String, jobId: UUID): Boolean
    suspend fun unSaveJob(userId: String, jobId: UUID): Boolean
    suspend fun isJobSaved(userId: String, jobId: UUID): Boolean
    suspend fun getSavedJobCount(userId: String): Int
}

