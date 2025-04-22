package net.jobsearchapplication_api.data.repository.devicetoken

import net.jobsearchapplication_api.base.BaseResponse

interface DeviceTokenRepository {
	suspend fun getToken(id: String) : BaseResponse<Any>
	suspend fun createToken(id: String,params:String) : BaseResponse<Any>

}