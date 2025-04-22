package net.jobsearchapplication_api.data.db.extensions

import net.jobsearchapplication_api.data.db.schemas.NotificationTable
import net.jobsearchapplication_api.data.db.schemas.*
import net.jobsearchapplication_api.data.models.*
import org.jetbrains.exposed.sql.ResultRow
import java.math.BigDecimal
import java.time.ZoneId
import java.util.*

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        fullName = this[UserTable.fullName] ?: "",
        phoneNumber = this[UserTable.phoneNumber] ?: "",
        avatar = this[UserTable.avatar] ?: "",
        bio = this[UserTable.bio] ?: "",
        birthDay = this[UserTable.birthDay]?.toString() ?: "",
        gender = this[UserTable.gender],
        cvUrl = this[UserTable.cvUrl] ?: "",
        favoritePosts = this[UserTable.favoriteJobPosting] ?: "",
        createdAt = this[UserTable.createdAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
        updatedAt = this[UserTable.updatedAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
        role = this[UserTable.role]
    )
}

fun ResultRow?.toStory(): Story? {
    return if (this == null) null
    else Story(
        id = this[StoryTable.id],
        title = this[StoryTable.title],
        content = this[StoryTable.content],
        isDraft = this[StoryTable.isDraft],
        createdAt = this[StoryTable.createdAt].toString()
    )
}

fun ResultRow?.toJobApplication(): JobApplication? {
    return if (this == null) null
    else JobApplication(
        userId = this[JobApplicationTable.userId],
        jobId = this[JobApplicationTable.jobId],
        status = this[JobApplicationTable.status],
        createdAt = this[JobApplicationTable.createdAt].toString(),
        additionalInfo = this[JobApplicationTable.additionalInfo].toString(),
        coverLetter = this[JobApplicationTable.coverLetter],
        cvUrl = this[JobApplicationTable.cvUrl]
    )
}


fun ResultRow?.toComment(): Comment? {
    return if (this == null) null
    else Comment(
        id = this[CommentTable.id],
        userId = this[CommentTable.userId],
        storyId = this[CommentTable.storyId],
        comment = this[CommentTable.comment],
        createdAt = this[CommentTable.createdAt].toString()
    )
}

// SavedJob Extensions
fun ResultRow?.toSavedJob(): SavedJob? {
    return if (this == null) null
    else SavedJob(
        id = this[PostTable.id],
        userId = this[PostTable.userId],
        jobId = this[PostTable.jobId],
        savedAt = this[PostTable.savedAt]
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
        name = this[JobCategoryTable.name],
        imageUrl = this[JobCategoryTable.jobCategoryImage].toString()
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
    val salaryMin: BigDecimal?,
    val salaryMax: BigDecimal?,
    val currency: String,
    val jobType: JobType,
    val createdAt: String
)
fun ResultRow.toJobWithDetails(): Job {
    return Job(
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
        categoryId = this[JobTable.jobCategory],
        postedBy = this[JobTable.postedBy],
        benefits = this[JobTable.benefits],
        quantity = this[JobTable.quantity],
        genderRequire = GenderRequirement.valueOf(this[JobTable.genderRequire]),
        deadline = this[JobTable.deadline],
        status = JobStatus.valueOf(this[JobTable.status]),
        requirements = this[JobTable.requirements],
        jobImage = this[JobTable.jobImage],
        createdAt = this[JobTable.createdAt]
    )
}

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
        categoryId = this[JobTable.jobCategory],
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


fun ResultRow?.toStoryJoinedWithUser(): Story? {
    return if (this == null) null
    else Story(
        id = this[StoryTable.id],
        user = User(
            fullName = this[UserTable.fullName],
            phoneNumber = this[UserTable.phoneNumber],
            avatar = this[UserTable.avatar],
            bio = this[UserTable.bio],
            birthDay = this[UserTable.birthDay].toString(),
            gender = this[UserTable.gender],
            cvUrl = this[UserTable.cvUrl],
            createdAt = this[UserTable.createdAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
            updatedAt = this[UserTable.updatedAt].atZone(ZoneId.systemDefault()).toLocalDateTime()
        ),
        title = this[StoryTable.title],
        content = this[StoryTable.content],
        isDraft = this[StoryTable.isDraft],
        createdAt = this[StoryTable.createdAt].toString()
    )
}

fun ResultRow.toAppliedUserWithApplication(): AppliedUserWithApplication {
    val jobApplication = this.toJobApplication()
    val user = this.toUser()
        return AppliedUserWithApplication(
            userId = jobApplication!!.userId,
            fullName = user?.fullName,
            phoneNumber = user?.phoneNumber,
            avatar = user?.avatar,
            bio = user?.bio,
            birthDay = user?.birthDay,
            gender = user?.gender,
            userCvUrl = user?.cvUrl,
            favoritePosts = user?.favoritePosts,
            userCreatedAt = user!!.createdAt,
            userUpdatedAt = user.updatedAt,
            role = user.role,
            jobId = jobApplication.jobId,
            applicationStatus = jobApplication.status,
            applicationCreatedAt = jobApplication.createdAt,
            coverLetter = jobApplication.coverLetter,
            additionalInfo = jobApplication.additionalInfo,
            applicationCvUrl = jobApplication.cvUrl
        )
}

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
        createdAt = this[CompanyTable.createdAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
        userId = this[CompanyTable.userId]
    )
}
fun ResultRow?.toNotification(): Notification? {
	return if (this == null) null
	else Notification(
		id = this[NotificationTable.id],
		userId = this[NotificationTable.userId],
		title = this[NotificationTable.title],
		description = this[NotificationTable.description],
		type = this[NotificationTable.type],
		relateId = this[NotificationTable.relatedId],
		isRead = this[NotificationTable.isRead],
		createAt = this[NotificationTable.createdAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
	)
}
fun ResultRow?.toDeviceTokens(): DeviceToken?{
	return if(this == null) null
	else DeviceToken(
		id = this[DeviceTokenTable.id],
		token = this[DeviceTokenTable.token]
	)
}