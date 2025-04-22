package net.jobsearchapplication_api.data.models

import net.jobsearchapplication_api.data.db.schemas.Gender
import java.math.BigDecimal
import java.time.LocalDateTime
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

data class AppliedUserWithApplication(
    // Thông tin từ User
    val userId: String,
    val fullName: String?,
    val phoneNumber: String?,
    val avatar: String?,
    val bio: String?,
    val birthDay: String?,
    val gender: Gender?,
    val userCvUrl: String?, // cvUrl từ User
    val favoritePosts: String?,
    val userCreatedAt: LocalDateTime,
    val userUpdatedAt: LocalDateTime,
    val role: String,

    // Thông tin từ JobApplication
    val jobId: UUID,
    val applicationStatus: String,
    val applicationCreatedAt: String,
    val coverLetter: String,
    val additionalInfo: String,
    val applicationCvUrl: String // cvUrl từ JobApplication
)
