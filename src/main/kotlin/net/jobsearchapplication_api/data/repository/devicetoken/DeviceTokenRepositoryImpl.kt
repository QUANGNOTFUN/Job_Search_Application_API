package net.jobsearchapplication_api.data.repository.devicetoken

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.ERROR_CREATE_COMPANY
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.devicetoken.DeviceTokenService
import net.jobsearchapplication_api.routes.devicetoken.DeviceTokenParams

class DeviceTokenRepositoryImpl(
	private val DeviceTokenService: DeviceTokenService
) : DeviceTokenRepository {
	override suspend fun getToken(id: String): BaseResponse<Any> {
		val token = DeviceTokenService.getToken(id)
		return BaseResponse.SuccessResponse(
			data = token,
			message = SUCCESS
		)
	}

	override suspend fun createToken(id: String,params:DeviceTokenParams): BaseResponse<Any> {
		val token = DeviceTokenService.createToken(id,params)
		val response = if (token != null) SUCCESS else ERROR_CREATE_COMPANY
		return BaseResponse.SuccessResponse(
			data = token,
			message = response
		)
	}
}