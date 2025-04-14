package net.jobsearchapplication_api.routes.job

import java.time.LocalDateTime
import java.util.*

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
    val salary: SalaryInfo,
    
    // Thông tin việc làm
    val employmentType: String, // FULL_TIME, PART_TIME, CONTRACT
    val location: LocationInfo,
    val experience: ExperienceInfo,
    
    // Thông tin bổ sung
    val deadline: LocalDateTime,
    val positionsAvailable: Int,
    val genderRequirement: String, // MALE, FEMALE, ANY
    val status: String = "ACTIVE", // ACTIVE, CLOSED, DRAFT

    // Thông tin thêm
    val additionalInfo: AdditionalInfo
)

data class SalaryInfo(
    val min: Double,
    val max: Double,
    val currency: String,
    val isNegotiable: Boolean
)

data class LocationInfo(
    val city: String,
    val district: String,
    val address: String,
    val isRemote: Boolean
)

data class ExperienceInfo(
    val minYears: Int,
    val maxYears: Int,
    val level: String // INTERN, FRESH, JUNIOR, SENIOR, LEAD
)

data class AdditionalInfo(
    val workingHours: String,
    val overtimePolicy: String?,
    val probationPeriod: String?,
    val jobImage: String?,
)

