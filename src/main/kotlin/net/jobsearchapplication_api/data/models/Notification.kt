package net.jobsearchapplication_api.data.models

import java.time.LocalDateTime
import java.util.UUID

data class Notification(
    val id: UUID,
    val userId: String,
    val title: String? = null,
    val message: String? = null,
    val type: String? = null,
    val relateId: UUID? = null,
    val isRead: Boolean? = null,
    val createAt: LocalDateTime = LocalDateTime.now()
)