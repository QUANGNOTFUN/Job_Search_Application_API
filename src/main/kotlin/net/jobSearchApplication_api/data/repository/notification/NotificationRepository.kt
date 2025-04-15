package net.jobSearchApplication_api.data.repository.notification

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.routes.notification.NotificationParams
import java.util.*

interface NotificationRepository {
	suspend fun getAllNotifications(page:Int,limit:Int):BaseResponse<Any>
	suspend fun getNotificationById(id: UUID):BaseResponse<Any>
	suspend fun createNotification(params: NotificationParams):BaseResponse<Any>
	suspend fun updateNotification(id: UUID, params: NotificationParams):BaseResponse<Any>
	suspend fun deleteNotification(params: UUID):BaseResponse<Any>
}