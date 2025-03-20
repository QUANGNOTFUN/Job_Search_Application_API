package net.jobsearchapplication_api.data.models

import java.time.LocalDateTime
import java.util.*

data class Company(
    val id: UUID,
    val name: String,
    val description: String,
    val location: String,
    val website: String,
    val logo: String? = null,
    val size: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val userId: UUID
)