package net.jobSearchApplication_api.data.service.notification

import net.jobSearchApplication_api.data.models.Notification
import net.jobSearchapplication_api.routes.notification.NotificationParams


interface NotificationService {
	suspend fun getAllNotificationByUserId(page:Int, limit: Int, userId: String):List<Notification>
	suspend fun createNotification(params: NotificationParams): Notification?
	suspend fun deleteNotification(id: Long):Boolean
	suspend fun updateNotification(id: Long):Notification?
}