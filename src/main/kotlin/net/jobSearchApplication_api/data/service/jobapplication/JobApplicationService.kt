package net.jobSearchApplication_api.data.service.jobapplication

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.data.models.JobApplication
import net.jobSearchApplication_api.routes.jobapplication.JobApplicationParams

interface JobApplicationService {
	suspend fun createJobApplication(params: JobApplicationParams): JobApplication?
	suspend fun getJobApplicationsByUserId(id: String): List<JobApplication?>
}