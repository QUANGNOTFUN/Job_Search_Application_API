package net.jobsearchapplication_api.data.models

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
data class User(
    val id: UUID,
    val fullName: String,
    val email: String,
    val passwordHash: String,
    val phoneNumber: String? = null,
    val avatar: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val cvUrl: String? = null,
    val education: String? = null,
    val experience: String? = null,
    var authToken: String? = null,
    var createdAt: String
)