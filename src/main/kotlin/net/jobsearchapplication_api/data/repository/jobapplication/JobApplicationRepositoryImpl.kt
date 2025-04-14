package net.jobsearchapplication_api.data.repository.jobapplication

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.GENERIC_ERROR
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.jobapplication.JobApplicationService
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams

class JobApplicationRepositoryImpl(private val jobApplicationService: JobApplicationService) : JobApplicationRepository {

    override suspend fun createJobApplication(params: JobApplicationParams): BaseResponse<Any> {
        val jobApplication = jobApplicationService.createJobApplication(params)
        return if(jobApplication != null){
            BaseResponse.SuccessResponse(data = jobApplication, message = SUCCESS)
        }else{
            BaseResponse.ErrorResponse(statusCode = HttpStatusCode.NotFound, message = GENERIC_ERROR)
        }
    }

}