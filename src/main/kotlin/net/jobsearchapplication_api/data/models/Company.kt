package net.jobsearchapplication_api.data.models

import java.time.LocalDateTime
import java.util.*

data class Company(
    val id: UUID,
    val name: String,
    val description: String? = null,
    val location: String? = null,
    val website: String? = null,
    val logo: String? = null,
    val size: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val userId: UUID? = null
)