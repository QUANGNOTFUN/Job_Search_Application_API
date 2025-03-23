package net.jobsearchapplication_api.data.models

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Job(
    val id: UUID,
    val title: String,
    val description: String,
    val salaryMin: BigDecimal?,
    val salaryMax: BigDecimal?,
    val currency: String = "VND",
    val location: String,
    val jobType: JobType,
    val experienceLevel: ExperienceLevel,
    val companyId: UUID,
    val postedBy: UUID,
    val benefits: String?,
    val quantity: Int = 1,
    val genderRequire: GenderRequirement,
    val deadline: LocalDateTime?,
    val status: JobStatus,
    val requirements: String?,
    val jobImage: String?,
    val createdAt: LocalDateTime
    )
// Enums cần thiết
enum class JobType {
    FULL_TIME,
    PART_TIME,
    CONTRACT,
    INTERNSHIP,
    FREELANCE
}

enum class ExperienceLevel {
    ENTRY,
    MID_LEVEL,
    SENIOR,
    LEADER,
    MANAGER
}

enum class GenderRequirement {
    MALE,
    FEMALE,
    ANY
}

enum class JobStatus {
    ACTIVE,
    CLOSED,
    DRAFT,
    EXPIRED
}