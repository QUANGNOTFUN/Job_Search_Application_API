package net.jobsearchapplication_api.data.repository.job

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.job.JobParams
import java.util.*

// JobRepository.kt
interface JobRepository {
    suspend fun getJobsByCompanyId(id: UUID): BaseResponse<Any>
    suspend fun getAllJobs(page: Int, limit: Int): BaseResponse<Any>
    suspend fun getJobById(id: UUID): BaseResponse<Any>
    suspend fun getJobsOfCategory(cateId: Int): BaseResponse<Any>
    suspend fun getFavoriteJobs(userId: String): BaseResponse<Any>
    suspend fun getPostedJobs(userId: String): BaseResponse<Any>

    suspend fun createJob(params: JobParams): BaseResponse<Any>

    suspend fun updateJob(id: UUID, params: JobParams): BaseResponse<Any>

    suspend fun deleteJob(id: UUID): BaseResponse<Any>

    suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): BaseResponse<Any>
}

