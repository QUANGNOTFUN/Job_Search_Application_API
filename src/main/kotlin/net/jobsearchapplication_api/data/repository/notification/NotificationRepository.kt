package net.jobsearchapplication_api.data.repository.notification

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.notification.NotificationParams
import java.util.*

interface NotificationRepository {
	suspend fun getAllNotificationByUserId(page:Int, limit:Int, id: String?):BaseResponse<Any>
	suspend fun createNotification(params: NotificationParams):BaseResponse<Any>
	suspend fun deleteNotification(params: String):BaseResponse<Any>
}