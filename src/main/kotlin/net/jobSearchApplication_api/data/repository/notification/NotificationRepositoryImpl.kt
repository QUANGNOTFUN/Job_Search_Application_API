package net.jobsearchapplication_api.data.repository.notification

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.config.ERROR_CREATE_COMPANY
import net.jobSearchApplication_api.config.SUCCESS
import net.jobSearchApplication_api.data.repository.notification.NotificationRepository
import net.jobSearchApplication_api.data.service.notification.NotificationService
import net.jobSearchApplication_api.routes.notification.NotificationParams
import java.util.*

class NotificationRepositoryImpl(
	private val NotificationService: NotificationService
) : NotificationRepository {

	override suspend fun getAllNotificationByUserId(page: Int, limit: Int, id: String?): BaseResponse<Any> {
		return try {
			if (id == null) {
				BaseResponse.ErrorResponse(
					message = "User ID is required"
				)
			} else {
				val notifications = NotificationService.getAllNotificationByUserId(page, limit, id)
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
		val notificationparams = NotificationService.createNotification(params)

		val response = if (notificationparams != null) SUCCESS else ERROR_CREATE_COMPANY

		return BaseResponse.SuccessResponse(
			data = notificationparams,
			message = response
		)
	}

	override suspend fun deleteNotification(id: String): BaseResponse<Any> {
		val deletedId = NotificationService.deleteNotification(id)
		return BaseResponse.SuccessResponse(
			data = deletedId,
			message = SUCCESS
		)
	}
}