package net.jobsearchapplication_api.routes.notification

import com.google.gson.annotations.SerializedName

data class NotificationParams(
	@SerializedName("userId") val userId: String ,
	@SerializedName("title") val title: String,
	@SerializedName("description") val description: String,
	@SerializedName("type") val type: String,
	@SerializedName("imageRes") val avatar: String,
	@SerializedName("createdAt") val createdAt: String,
	@SerializedName("senderId") val senderId: String,
	@SerializedName("senderName") val senderName: String,
	@SerializedName("isRead") val isRead: Boolean,
	@SerializedName("jobId") val relateId: String
)
