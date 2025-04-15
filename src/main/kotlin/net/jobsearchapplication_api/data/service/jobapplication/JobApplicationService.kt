package net.jobsearchapplication_api.data.service.jobapplication

import net.jobsearchapplication_api.data.models.JobApplication
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams
import java.util.UUID

interface JobApplicationService {
    suspend fun createJobApplication(params: JobApplicationParams): JobApplication?

    suspend fun getJobApplicationsByUserId(id: String): List<JobApplication?>
}