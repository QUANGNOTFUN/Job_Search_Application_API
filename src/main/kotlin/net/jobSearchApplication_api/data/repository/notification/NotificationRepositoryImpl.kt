package net.jobsearchapplication_api.data.repository.notification

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.config.SUCCESS
import net.jobSearchApplication_api.data.repository.notification.NotificationRepository
import net.jobSearchApplication_api.data.service.notification.NotificationService
import net.jobSearchapplication_api.routes.notification.NotificationParams


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

	override suspend fun updateNotification(id: Long): BaseResponse<Any> {
		val response = NotificationService.updateNotification(id)
		return BaseResponse.SuccessResponse(
			data = response,
			message = SUCCESS
		)
	}
}