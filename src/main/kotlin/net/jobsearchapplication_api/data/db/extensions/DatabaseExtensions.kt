package net.jobsearchapplication_api.data.db.extensions

import net.jobsearchapplication_api.data.db.schemas.*
import net.jobsearchapplication_api.data.models.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.ZoneId
import java.util.UUID

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        email = this[UserTable.email],
        passwordHash = this[UserTable.password_hash],
        phoneNumber = this[UserTable.phone_number],
        avatar = this[UserTable.avatar],
        bio = this[UserTable.bio],
        gender = this[UserTable.gender],
        location = this[UserTable.location],
        cvUrl = this[UserTable.cv_url],
        education = this[UserTable.education],
        experience = this[UserTable.experience],
        createdAt = this[UserTable.createdAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
        updatedAt = this[UserTable.updatedAt].atZone(ZoneId.systemDefault()).toLocalDateTime()
    )
}

// Company Extensions
fun ResultRow?.toCompany(): Company? {
    return if (this == null) null
    else Company(
        id = this[CompanyTable.id],
        name = this[CompanyTable.name],
        description = this[CompanyTable.description],
        location = this[CompanyTable.location],
        website = this[CompanyTable.website],
        logo = this[CompanyTable.logo],
        size = this[CompanyTable.size],
        createdAt = this[CompanyTable.createdAt],
        userId = this[CompanyTable.userId]
    )
}

// Job Extensions
fun ResultRow?.toJob(): Job? {
    return if (this == null) null
    else Job(
        id = this[JobTable.id],
        title = this[JobTable.title],
        description = this[JobTable.description],
        salaryMin = this[JobTable.salaryMin],
        salaryMax = this[JobTable.salaryMax],
        currency = this[JobTable.currency],
        location = this[JobTable.location],
        jobType = JobType.valueOf(this[JobTable.jobType]),
        experienceLevel = ExperienceLevel.valueOf(this[JobTable.experienceLevel]),
        companyId = this[JobTable.companyId],
        createdAt = this[JobTable.createdAt],
        postedBy = this[JobTable.postedBy],
        benefits = this[JobTable.benefits],
        quantity = this[JobTable.quantity],
        genderRequire = GenderRequirement.valueOf(this[JobTable.genderRequire]),
        deadline = this[JobTable.deadline],
        status = JobStatus.valueOf(this[JobTable.status]),
        requirements = this[JobTable.requirements],
        jobImage = this[JobTable.jobImage]
    )
}

// Job với thông tin công ty
fun ResultRow?.toJobWithCompany(): JobWithCompany? {
    return if (this == null) null
    else JobWithCompany(
        job = this.toJob()!!,
        company = this.toCompany()!!
    )
}

// SavedJob Extensions
fun ResultRow?.toSavedJob(): SavedJob? {
    return if (this == null) null
    else SavedJob(
        id = this[SavedJobTable.id],
        userId = this[SavedJobTable.userId],
        jobId = this[SavedJobTable.jobId],
        savedAt = this[SavedJobTable.savedAt]
    )
}

// Message Extensions
fun ResultRow?.toMessage(): Message? {
    return if (this == null) null
    else Message(
        id = this[MessageTable.id],
        senderId = this[MessageTable.senderId],
        receiverId = this[MessageTable.receiverId],
        message = this[MessageTable.message],
        sentAt = this[MessageTable.sentAt],
        isRead = this[MessageTable.isRead]
    )
}

// JobCategory Extensions
fun ResultRow?.toJobCategory(): JobCategory? {
    return if (this == null) null
    else JobCategory(
        id = this[JobCategoryTable.id],
        name = this[JobCategoryTable.name]
    )
}

// Job Summary (cho danh sách)
fun ResultRow?.toJobSummary(): JobSummary? {
    return if (this == null) null
    else JobSummary(
        id = this[JobTable.id],
        title = this[JobTable.title],
        companyName = this[CompanyTable.name],
        location = this[JobTable.location],
        salaryMin = this[JobTable.salaryMin],
        salaryMax = this[JobTable.salaryMax],
        currency = this[JobTable.currency],
        jobType = JobType.valueOf(this[JobTable.jobType]),
        createdAt = this[JobTable.createdAt]
    )
}

// Data class cho JobWithCompany
data class JobWithCompany(
    val job: Job,
    val company: Company
)

// Data class cho JobSummary
data class JobSummary(
    val id: UUID,
    val title: String,
    val companyName: String,
    val location: String,
    val salaryMin: java.math.BigDecimal?,
    val salaryMax: java.math.BigDecimal?,
    val currency: String,
    val jobType: JobType,
    val createdAt: java.time.LocalDateTime
)