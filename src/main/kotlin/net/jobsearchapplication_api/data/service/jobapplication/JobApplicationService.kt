package net.jobsearchapplication_api.data.service.jobapplication

import net.jobsearchapplication_api.data.models.JobApplication
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams
import java.util.*

interface JobApplicationService {
    suspend fun createJobApplication(params: JobApplicationParams): JobApplication?
    suspend fun getJobApplicationsByUserId(id: String): List<JobApplication?>
    suspend fun getAppliedUsersByJobId(jobId: UUID): List<Any?>
}
