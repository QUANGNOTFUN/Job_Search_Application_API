package net.jobsearchapplication_api.data.repository.notification

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.notification.NotificationParams
import java.util.*

interface NotificationRepository {
	suspend fun getAllNotifications(page:Int,limit:Int):BaseResponse<Any>
	suspend fun getNotificationById(id: UUID):BaseResponse<Any>
	suspend fun createNotification(params: NotificationParams):BaseResponse<Any>
	suspend fun updateNotification(id: UUID, params: NotificationParams):BaseResponse<Any>
	suspend fun deleteNotification(params: UUID):BaseResponse<Any>
}