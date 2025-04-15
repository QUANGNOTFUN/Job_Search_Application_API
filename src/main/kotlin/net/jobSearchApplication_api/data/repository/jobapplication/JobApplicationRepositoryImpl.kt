package net.jobsearchapplication_api.data.repository.jobapplication

import io.ktor.http.*
import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.config.GENERIC_ERROR
import net.jobSearchApplication_api.config.SUCCESS
import net.jobSearchApplication_api.data.repository.jobapplication.JobApplicationRepository
import net.jobSearchApplication_api.data.service.jobapplication.JobApplicationService
import net.jobSearchApplication_api.routes.jobapplication.JobApplicationParams
import java.util.*

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


}