package net.jobSearchApplication_api.data.service.notification

import net.jobSearchApplication_api.data.models.Notification
import net.jobSearchApplication_api.routes.notification.NotificationParams
import java.util.UUID

interface NotificationService {
	suspend fun getAllNotificationByUserId(page:Int, limit: Int, id: String):List<Notification>
	suspend fun createNotification(params: NotificationParams):Notification?
	suspend fun deleteNotification(id: String):Boolean
}