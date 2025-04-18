package net.jobSearchApplication_api.data.service.favorite

import net.jobSearchApplication_api.data.db.extensions.JobWithCompany
import net.jobsearchapplication_api.routes.user.FavoriteParams
import java.util.*

interface FavoriteService {
    suspend fun getSavedJobs(userId: String): List<JobWithCompany>
    suspend fun saveJob(userId: String, jobId: UUID): Boolean
    suspend fun unSaveJob(userId: String, jobId: UUID): Boolean
    suspend fun isJobSaved(userId: String, jobId: UUID): Boolean
    suspend fun getSavedJobCount(userId: String): Int

    suspend fun favoriteJobPosting(uuid: String, params: FavoriteParams)
}

