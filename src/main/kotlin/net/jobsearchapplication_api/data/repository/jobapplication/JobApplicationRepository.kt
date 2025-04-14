package net.jobsearchapplication_api.data.repository.jobapplication

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams

interface JobApplicationRepository {
    suspend fun createJobApplication(params: JobApplicationParams): BaseResponse<Any>
}