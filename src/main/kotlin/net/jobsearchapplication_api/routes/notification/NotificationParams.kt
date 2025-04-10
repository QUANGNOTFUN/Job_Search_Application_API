package net.jobsearchapplication_api.routes.notification

import net.jobsearchapplication_api.data.models.Message
import java.util.UUID

data class NotificationParams (
    val userId: String,
    val title: String,
    val message: String,
    val type:String,
)
