package net.jobsearchapplication_api.data.repository.savejob

import net.jobsearchapplication_api.base.BaseResponse
import java.util.*

// data/repository/savedjob/SavedJobRepository.kt
interface SavedJobRepository {
    suspend fun getSavedJobs(userId: UUID): BaseResponse<Any>
    suspend fun saveJob(userId: UUID, jobId: UUID): BaseResponse<Any>
    suspend fun unsaveJob(userId: UUID, jobId: UUID): BaseResponse<Any>
    suspend fun isJobSaved(userId: UUID, jobId: UUID): BaseResponse<Any>
    suspend fun getSavedJobCount(userId: UUID): BaseResponse<Any>
}

