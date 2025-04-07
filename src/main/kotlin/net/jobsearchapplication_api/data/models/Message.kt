package net.jobsearchapplication_api.data.models

import java.time.LocalDateTime
import java.util.*

data class Message(
    val id: UUID,
    val senderId: String,
    val receiverId: String,
    val message: String,
    val sentAt: LocalDateTime = LocalDateTime.now(),
    val isRead: Boolean = false
)