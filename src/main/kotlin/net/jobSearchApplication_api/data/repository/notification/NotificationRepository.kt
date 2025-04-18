package net.jobSearchApplication_api.data.repository.notification

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchapplication_api.routes.notification.NotificationParams


interface NotificationRepository {
	suspend fun getAllNotificationByUserId(page:Int, limit:Int, userId: String): BaseResponse<Any>
	suspend fun createNotification(params: NotificationParams):BaseResponse<Any>
	suspend fun deleteNotification(id: Long):BaseResponse<Any>
	suspend fun updateNotification(id:Long): BaseResponse<Any>
}