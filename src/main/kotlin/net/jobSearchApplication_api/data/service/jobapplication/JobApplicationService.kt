package net.jobSearchApplication_api.data.service.jobapplication

import net.jobSearchApplication_api.data.models.JobApplication
import net.jobSearchApplication_api.routes.jobapplication.JobApplicationParams

interface JobApplicationService {
    suspend fun createJobApplication(params: JobApplicationParams): JobApplication?
    abstract fun getJobApplicationsByUserId(id: String): Any?
}