package net.jobsearchapplication_api.data.repository.jobapplication

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.GENERIC_ERROR
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.jobapplication.JobApplicationService
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams
import java.util.UUID

class JobApplicationRepositoryImpl(
    private val jobApplicationService: JobApplicationService
) :
    JobApplicationRepository {

    override suspend fun createJobApplication(params: JobApplicationParams): BaseResponse<Any> {
        val jobApplication = jobApplicationService.createJobApplication(params)
        return if(jobApplication != null){
            BaseResponse.SuccessResponse(data = jobApplication, message = SUCCESS)
        }else{
            BaseResponse.ErrorResponse(statusCode = HttpStatusCode.NotFound, message = GENERIC_ERROR)
        }
    }

    override suspend fun getJobApplicationsByUserId(id: String): BaseResponse<Any> {
        val jobApplication = jobApplicationService.getJobApplicationsByUserId(id)
        return BaseResponse.SuccessResponse(data = jobApplication, message = SUCCESS)
    }

    override suspend fun getAppliedUsersByJobId(jobId: UUID): BaseResponse<Any> {
        val jobApplication = jobApplicationService.getAppliedUsersByJobId(jobId)
        return BaseResponse.SuccessResponse(data = jobApplication, message = SUCCESS)
    }


}