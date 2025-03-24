package net.jobsearchapplication_api.data.service.job

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.db.extensions.JobWithCompany
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.data.models.SavedJob
import net.jobsearchapplication_api.data.models.Story
import net.jobsearchapplication_api.data.models.common.PaginatedResult
import net.jobsearchapplication_api.routes.job.JobParams
import java.util.*

interface JobService {
    suspend fun getAllJobs(page: Int, limit: Int): PaginatedResult<Job>
    suspend fun getJobById(id: UUID): Job?
    suspend fun getJobsByUserId(id: UUID): Job?
    suspend fun getJobsByCompanyId(companyId: UUID): List<Job>

    suspend fun createJob(params: JobParams): Job?

    suspend fun updateJob(id: UUID, params: JobParams): Job?

    suspend fun deleteJob(id: UUID): Boolean

    suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): List<Job>
}

