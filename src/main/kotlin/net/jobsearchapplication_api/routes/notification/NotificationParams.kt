package net.jobsearchapplication_api.routes.notification


data class NotificationParams (
    val userId: String,
    val title: String,
    val description: String,
    val type:String,
)
