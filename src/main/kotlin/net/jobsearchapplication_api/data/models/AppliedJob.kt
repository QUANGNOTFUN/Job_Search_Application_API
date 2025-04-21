package net.jobsearchapplication_api.data.models

import java.math.BigDecimal
import java.util.UUID

data class AppliedJob(
    val id: UUID,
    val title: String,
    val salaryMin: BigDecimal?,
    val salaryMax: BigDecimal?,
    val currency: String = "VND",
    val location: String,
    val jobImage: String?,
    val statusResponse: String,
    val createdAt: String
)
