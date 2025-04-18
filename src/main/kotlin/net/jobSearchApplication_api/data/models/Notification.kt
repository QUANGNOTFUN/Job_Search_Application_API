package net.jobSearchApplication_api.data.models

import java.time.LocalDateTime

data class Notification(
	val id: Long,
	val userId: String,
	val title: String? = null,
	val senderId: String? = null,
	val senderName:String? = null,
	val description: String? = null,
	val type: String? = null,
	val imageRes: Int? = null,
	val relateId: String? = null,
	val isRead: Boolean? = null,
	val createdAt: LocalDateTime = LocalDateTime.now()
)