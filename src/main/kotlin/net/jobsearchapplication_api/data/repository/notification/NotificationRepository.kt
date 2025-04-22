package net.jobsearchapplication_api.data.repository.notification

import net.jobsearchapplication_api.routes.notification.NotificationParams
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.notification.NotificationParams


interface NotificationRepository {
	suspend fun getAllNotificationByUserId(page:Int, limit:Int, userId: String): BaseResponse<Any>
	suspend fun createNotification(params: NotificationParams):BaseResponse<Any>
	suspend fun deleteNotification(id:Long):BaseResponse<Any>
	suspend fun updateNotification(id:Long,isRead: Boolean): BaseResponse<Any>
}