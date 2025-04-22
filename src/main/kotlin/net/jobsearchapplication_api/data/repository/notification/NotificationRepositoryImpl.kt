package net.jobsearchapplication_api.data.repository.notification


import net.jobsearchapplication_api.data.service.notification.NotificationService
import net.jobsearchapplication_api.routes.notification.NotificationParams
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.SUCCESS


class NotificationRepositoryImpl(
	private val notificationService: NotificationService
) : NotificationRepository {

	override suspend fun getAllNotificationByUserId(page: Int, limit: Int, userId: String): BaseResponse<Any> {
		return try {
			run {
				val notifications = notificationService.getAllNotificationByUserId(page, limit, userId)
				BaseResponse.SuccessResponse(
					data = notifications,
					message = SUCCESS
				)
			}
		} catch (e: Exception) {
			println("Error in getAllNotificationByUserId: ${e.message}")
			BaseResponse.ErrorResponse(
				message = "Failed to fetch notifications: ${e.message}"
			)
		}
	}

	override suspend fun createNotification(params: NotificationParams): BaseResponse<Any> {
		return try {
			val notification =notificationService.createNotification(params)
			BaseResponse.SuccessResponse(
				data = notification,
				message = SUCCESS
			)
		} catch (e: Exception) {
			BaseResponse.ErrorResponse(
				message = "Failed to create notification: ${e.message}"
			)
		}
	}

	override suspend fun deleteNotification(id: Long): BaseResponse<Any> {
		val deletedId =notificationService.deleteNotification(id)
		return BaseResponse.SuccessResponse(
			data = deletedId,
			message = SUCCESS
		)
	}

	override suspend fun updateNotification(id: Long, isRead: Boolean): BaseResponse<Any> {
		return try {
			val success = notificationService.updateNotification(id, isRead)
			if (success) {
				BaseResponse.SuccessResponse(
					data = true,
					message = "Notification updated successfully"
				)
			} else {
				BaseResponse.ErrorResponse(
					message = "Notification with id $id not found"
				)
			}
		} catch (e: Exception) {
			BaseResponse.ErrorResponse(
				message = "Error updating notification: ${e.message}"
			)
		}
	}
}