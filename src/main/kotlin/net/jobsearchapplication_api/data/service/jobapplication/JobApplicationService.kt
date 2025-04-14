package net.jobsearchapplication_api.data.service.jobapplication

import net.jobsearchapplication_api.data.models.JobApplication
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams

interface JobApplicationService {
    suspend fun createJobApplication(params: JobApplicationParams): JobApplication?
}