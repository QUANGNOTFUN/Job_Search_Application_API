package net.jobsearchapplication_api.data.repository.job

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.job.JobService
import net.jobsearchapplication_api.routes.job.JobParams
import java.util.*

// JobRepositoryImpl.kt
class JobRepositoryImpl(
    private val jobService: JobService
) : JobRepository {
    override suspend fun getAllJobs(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(
            data = jobService.getAllJobs(page, limit),
            message = SUCCESS
        )
    }

    override suspend fun getJobById(id: UUID): BaseResponse<Any> {
        val job = jobService.getJobById(id)
        return if (job != null) {
            BaseResponse.SuccessResponse(data = job, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = "Không tìm thấy công việc")
        }
    }

    override suspend fun createJob(params: JobParams): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateJob(id: UUID, params: JobParams): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteJob(id: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    // Implement các phương thức khác...
}