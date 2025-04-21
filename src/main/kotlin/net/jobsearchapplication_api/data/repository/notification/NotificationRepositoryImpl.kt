package net.jobSearchapplication_api.data.repository.notification


import net.jobSearchApplication_api.data.repository.notification.NotificationRepository
import net.jobSearchApplication_api.data.service.notification.NotificationService
import net.jobSearchapplication_api.routes.notification.NotificationParams
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.SUCCESS


class NotificationRepositoryImpl(
	private val NotificationService: NotificationService
) : NotificationRepository {

	override suspend fun getAllNotificationByUserId(page: Int, limit: Int, userId: String): BaseResponse<Any> {
		return try {
			run {
				val notifications = NotificationService.getAllNotificationByUserId(page, limit, userId)
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
			val notification = NotificationService.createNotification(params)
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
		val deletedId = NotificationService.deleteNotification(id)
		return BaseResponse.SuccessResponse(
			data = deletedId,
			message = SUCCESS
		)
	}

	override suspend fun updateNotification(id: Long, isRead: Boolean): BaseResponse<Any> {
		return try {
			val success = NotificationService.updateNotification(id, isRead)
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