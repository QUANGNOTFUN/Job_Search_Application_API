package net.jobsearchapplication_api.routes.job

import net.jobsearchapplication_api.data.models.ExperienceLevel
import java.time.LocalDateTime

data class JobParams(
    // Thông tin cơ bản
    val companyId: String,
    val title: String,
    val description: String,
    val requirements: String,
    val benefits: String,
    val postedBy: String,
    val categoryId: Int,
    
    // Thông tin lương
    val salaryMin: Double,
    val salaryMax: Double,
    val salaryPeriod: String,

    // Thông tin việc làm
    val employmentType: String, // FULL_TIME, PART_TIME, CONTRACT
    val location: String,
    val experienceLevel: String,
    val currency: String,

    // Thông tin bổ sung
    val deadline: LocalDateTime,
    val positionsAvailable: Int,
    val genderRequirement: String, // MALE, FEMALE, ANY
    val status: String = "ACTIVE", // ACTIVE, CLOSED, DRAFT

    // Thông tin thêm
    val additionalInfo: AdditionalInfo?
)

data class AdditionalInfo(
    val workingHours: String,
    val overtimePolicy: String?,
    val probationPeriod: String?,
    val jobImage: String?,
)

