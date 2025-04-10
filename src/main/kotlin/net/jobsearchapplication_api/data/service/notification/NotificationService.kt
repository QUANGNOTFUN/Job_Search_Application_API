package net.jobsearchapplication_api.data.service.notification

import net.jobsearchapplication_api.data.models.Notification
import net.jobsearchapplication_api.routes.notification.NotificationParams
import java.util.UUID

interface NotificationService {
	suspend fun getAllNotifications(page:Int, limit: Int):List<Notification>
	suspend fun getNotificationById(Id:UUID):Notification?
	suspend fun createNotification(params: NotificationParams):Notification?
	suspend fun updateNotification(id:UUID,params: NotificationParams):Notification?
	suspend fun deleteNotification(id: UUID):Boolean
}