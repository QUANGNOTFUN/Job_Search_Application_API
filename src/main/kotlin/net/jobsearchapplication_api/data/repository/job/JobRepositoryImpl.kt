package net.jobsearchapplication_api.data.repository.job

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.GENERIC_ERROR
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.extensions.toJob
import net.jobsearchapplication_api.data.db.schemas.JobTable
import net.jobsearchapplication_api.data.models.common.PaginatedResult
import net.jobsearchapplication_api.data.service.job.JobService
import net.jobsearchapplication_api.routes.job.JobParams
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.selectAll
import java.util.*
import kotlin.math.ceil

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
//            message = SUCCESS
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

    override suspend fun createJob(jobParams: JobParams): BaseResponse<Any> {
        val job = jobService.createJob(jobParams)
        return if(job != null){
            BaseResponse.SuccessResponse(data = job, message = SUCCESS)
        }else{
            BaseResponse.ErrorResponse(statusCode = HttpStatusCode.NotFound, message = GENERIC_ERROR)
        }
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

}