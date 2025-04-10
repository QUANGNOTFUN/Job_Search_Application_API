package net.jobsearchapplication_api.data.models

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import java.util.*
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Company(
    val id: String,
    val name: String,
    val description: String? = null,
    val location: String? = null,
    val website: String? = null,
    val logo: String? = null,
    val size: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val userId: String? = null
)