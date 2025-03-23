package net.jobsearchapplication_api.data.service.job

import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.routes.job.JobParams
import java.util.*

interface JobService {
    suspend fun getAllJobs(page: Int, limit: Int): List<Job>
    suspend fun getJobById(id: UUID): Job?
    suspend fun createJob(params: JobParams): Job?
    suspend fun updateJob(id: UUID, params: JobParams): Boolean
    suspend fun deleteJob(id: UUID): Boolean
    suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): List<Job>
    suspend fun getJobsByCompany(companyId: UUID): List<Job>
}

