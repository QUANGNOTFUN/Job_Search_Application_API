package net.jobSearchApplication_api.data.repository.notification

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.routes.notification.NotificationParams
import java.util.*

interface NotificationRepository {
	suspend fun getAllNotificationByUserId(page:Int, limit:Int, id: String?):BaseResponse<Any>
	suspend fun createNotification(params: NotificationParams):BaseResponse<Any>
	suspend fun deleteNotification(params: String):BaseResponse<Any>
}