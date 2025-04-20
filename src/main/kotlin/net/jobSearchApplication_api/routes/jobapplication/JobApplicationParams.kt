package net.jobSearchApplication_api.routes.jobapplication

import java.util.UUID

data class JobApplicationParams(
    val jobId: UUID,
    val userId: String,
    val status: String,
    val coverLetter: String,
    val cvUrl: String,
    val additionalInfo: String
)
