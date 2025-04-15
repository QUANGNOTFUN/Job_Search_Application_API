package net.jobSearchApplication_api.routes.notification


data class NotificationParams (
	val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val type:String,
)
