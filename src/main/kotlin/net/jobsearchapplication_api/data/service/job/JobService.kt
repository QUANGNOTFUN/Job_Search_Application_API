package net.jobsearchapplication_api.data.service.job

import net.jobsearchapplication_api.data.models.AppliedJob
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.data.models.common.PaginatedResult
import net.jobsearchapplication_api.routes.job.JobParams
import java.util.*

interface JobService {
    suspend fun getAllJobs(page: Int, limit: Int): PaginatedResult<Job>

    suspend fun getJobById(id: UUID): Job?

    suspend fun getJobsByUserId(id: UUID): Job?

    suspend fun getJobsByCompanyId(companyId: UUID): List<Job>

    suspend fun getJobsByCategory(cateId: Int): List<Job>

    suspend fun getAppliedJobs(userId: String): List<AppliedJob?>

    suspend fun getFavoriteJobs(userId: String): List<Job?>

    suspend fun getPostedJobs(userId: String): List<Job?>

    suspend fun createJob(params: JobParams): Job?

    suspend fun updateJob(id: UUID, params: JobParams): Job?

    suspend fun deleteJob(id: UUID): Boolean

    suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): PaginatedResult<Job>
}

