package net.jobsearchapplication_api.data.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import net.jobsearchapplication_api.data.db.schemas.Gender
import net.jobsearchapplication_api.data.db.schemas.UserRole
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
data class User(
    val id: String,
    val fullName: String? = null,
    val phoneNumber: String? = null,
    val avatar: String? = null,
    val bio: String? = null,
    val gender: Gender?,
    val location: String? = null,
    val cvUrl: String? = null,
    val education: String? = null,
    val experience: String? = null,
    var authToken: String? = null,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    val role: String = UserRole.USER.name
)