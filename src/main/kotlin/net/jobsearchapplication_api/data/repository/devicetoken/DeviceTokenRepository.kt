package net.jobsearchapplication_api.data.repository.devicetoken

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.devicetoken.DeviceTokenParams

interface DeviceTokenRepository {
	suspend fun getToken(id: String) : BaseResponse<Any>
	suspend fun createToken(id: String,params:DeviceTokenParams) : BaseResponse<Any>

}