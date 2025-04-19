package net.jobSearchApplication_api.data.models

import java.util.*

data class JobApplication(
    val userId: String,
    val jobId: UUID,
    val status: String,
    val createdAt: String,
    val coverLetter: String,
    val cvUrl: String? = null
    )