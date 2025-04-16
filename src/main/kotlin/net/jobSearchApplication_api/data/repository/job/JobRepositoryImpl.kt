package net.jobSearchApplication_api.data.repository.job

import io.ktor.http.*
import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.config.GENERIC_ERROR
import net.jobSearchApplication_api.config.SUCCESS
import net.jobSearchApplication_api.data.models.GenderRequirement
import net.jobSearchApplication_api.data.models.JobType
import net.jobSearchApplication_api.data.service.job.JobService
import net.jobSearchApplication_api.routes.job.JobParams
import java.time.LocalDateTime
import java.util.*

// JobRepositoryImpl.kt
class JobRepositoryImpl(private val jobService: JobService ) : JobRepository {

    override suspend fun getAllJobs(page: Int, limit: Int): BaseResponse<Any> {
        return try {
            BaseResponse.SuccessResponse(data = jobService.getAllJobs(page, limit), message = SUCCESS)
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(
                message = "Error getting jobs: ${e.localizedMessage}"
            )
        }
    }

    override suspend fun getJobsByCompanyId(id: UUID): BaseResponse<Any> {
//        val job = jobService.getJobById(id)
//        return BaseResponse.SuccessResponse(
//            data = jobService.getJobsByCompanyId(id)
//            description = SUCCESS
//        )

        TODO("Not yet implemented")

    }

    override suspend fun getJobById(id: UUID): BaseResponse<Any> {
        val job = jobService.getJobById(id)
        return if (job != null) {
            BaseResponse.SuccessResponse(data = job, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = "Không tìm thấy công việc")
        }
    }

    override suspend fun getJobsOfCategory(cateId: Int): BaseResponse<Any> {
        return try {
            BaseResponse.SuccessResponse(data = jobService.getJobsOfCategory(cateId), message = SUCCESS)
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(statusCode = HttpStatusCode.BadRequest ,message = "Lỗi: ${e.message}")
        }
    }

    override suspend fun createJob(params: JobParams): BaseResponse<Any> {
        val job = jobService.createJob(params)
        return if(job != null){
            BaseResponse.SuccessResponse(data = job, message = SUCCESS)
        }else{
            BaseResponse.ErrorResponse(statusCode = HttpStatusCode.NotFound, message = GENERIC_ERROR)
        }
    }

    override suspend fun updateJob(id: UUID, params: JobParams): BaseResponse<Any> {
        return try {
            // Validate input
            val validationErrors = validateJobParams(params)
            if (validationErrors.isNotEmpty()) {
                return BaseResponse.ErrorResponse(
                    message = "Validation failed: ${validationErrors.joinToString(", ")}"
                )
            }

            // Kiểm tra job tồn tại
            val existingJob = jobService.getJobById(id)
            if (existingJob == null) {
                return BaseResponse.ErrorResponse(message = "Job not found")
            }

            // Thực hiện update
            val updatedJob = jobService.updateJob(id, params)
            if (updatedJob != null) {
                BaseResponse.SuccessResponse(
                    data = updatedJob,
                    message = "Job updated successfully"
                )
            } else {
                BaseResponse.ErrorResponse(message = "Failed to update job")
            }
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Error updating job: ${e.localizedMessage}")
        }
    }

    private fun validateJobParams(params: JobParams): List<String> {
        val errors = mutableListOf<String>()

        // Validate title
        if (params.title.isBlank()) {
            errors.add("Title is required")
        }

        // Validate description
        if (params.description.isBlank()) {
            errors.add("Description is required")
        }

        // Validate salary
        if (params.salary.min > params.salary.max) {
            errors.add("Minimum salary cannot be greater than maximum salary")
        }

        // Validate deadline
        params.deadline.let {
            if (it.isBefore(LocalDateTime.now())) {
                errors.add("Deadline must be in the future")
            }
        }

        // Validate job type
        try {
            JobType.valueOf(params.employmentType)
        } catch (e: IllegalArgumentException) {
            errors.add("Invalid job type")
        }

        // Validate gender requirement
        try {
            GenderRequirement.valueOf(params.genderRequirement)
        } catch (e: IllegalArgumentException) {
            errors.add("Invalid gender requirement")
        }

        return errors
    }

    override suspend fun deleteJob(id: UUID): BaseResponse<Any> {
        if (jobService.deleteJob(id)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

}