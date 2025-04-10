package net.jobsearchapplication_api.data.repository.savejob

import net.jobsearchapplication_api.base.BaseResponse
import java.util.*

interface SavedJobRepository {
    suspend fun getSavedJobs(userId: String, page: Int, limit: Int): BaseResponse<Any>
    suspend fun saveJob(userId: String, jobId: UUID): BaseResponse<Any>
    suspend fun unSaveJob(userId: String, jobId: UUID): BaseResponse<Any>
    suspend fun isJobSaved(userId: String, jobId: UUID): BaseResponse<Any>
    suspend fun getSavedJobCount(userId: String): BaseResponse<Any>
}

