package net.jobsearchapplication_api.data.repository.jobapplication

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams
import java.util.UUID

interface JobApplicationRepository {
    suspend fun createJobApplication(params: JobApplicationParams): BaseResponse<Any>
    suspend fun getJobApplicationsByUserId(id: String): BaseResponse<Any>
    suspend fun getJobApplicationsByUserIdAndJobId(userId: String, jobId: UUID): BaseResponse<Any>

}