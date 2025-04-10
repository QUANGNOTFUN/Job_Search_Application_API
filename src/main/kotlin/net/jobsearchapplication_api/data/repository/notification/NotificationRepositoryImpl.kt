package net.jobsearchapplication_api.data.repository.notification

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.ERROR_CREATE_COMPANY
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.notification.NotificationService
import net.jobsearchapplication_api.routes.notification.NotificationParams
import java.util.*

class NotificationRepositoryImpl(
	private val NotificationService: NotificationService
) :NotificationRepository{

	override suspend fun getAllNotifications(page: Int, limit: Int): BaseResponse<Any> {
		return BaseResponse.SuccessResponse(
			data = NotificationService.getAllNotifications(page, limit),
			message = SUCCESS
		)
	}

	override suspend fun getNotificationById(id: UUID): BaseResponse<Any> {
		val companyId = NotificationService.getNotificationById(id)
		return BaseResponse.SuccessResponse(
			data = companyId,
			message = SUCCESS
		)
	}

	override suspend fun createNotification(params: NotificationParams): BaseResponse<Any> {
		val company = NotificationService.createNotification(params)

		val response = if (company != null) SUCCESS else ERROR_CREATE_COMPANY

		return BaseResponse.SuccessResponse(
			data = company,
			message = response
		)
	}

	override suspend fun updateNotification(id: UUID, params: NotificationParams): BaseResponse<Any> {
		val companyId = NotificationService.updateNotification(id, params)
		return BaseResponse.SuccessResponse(
			data = companyId,
			message = SUCCESS
		)
	}

	override suspend fun deleteNotification(id: UUID): BaseResponse<Any> {
		val deletedId = NotificationService.deleteNotification(id)
		return BaseResponse.SuccessResponse(
			data = deletedId,
			message = SUCCESS
		)
	}
}