package net.jobsearchapplication_api.routes.jobapplication

import java.util.UUID

data class JobApplicationParams(
    val jobId: UUID,
    val userId: String,
    val status: String,
    val coverLetter: String,
    val cvUrl: String,
    val additionalInfo: String
)

data class UpdateAppliedStatus(
    val userId: String,
    val jobId: UUID,
    val status: String
)
