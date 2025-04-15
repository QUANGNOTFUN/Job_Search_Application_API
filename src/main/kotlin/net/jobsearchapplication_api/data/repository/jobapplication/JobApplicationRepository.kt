package net.jobSearchApplication_api.data.repository.jobapplication

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.routes.jobapplication.JobApplicationParams

interface JobApplicationRepository {
    suspend fun createJobApplication(params: JobApplicationParams): BaseResponse<Any>
    suspend fun getJobApplicationsByUserId(id: String): BaseResponse<Any>
}