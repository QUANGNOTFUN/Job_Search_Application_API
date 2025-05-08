package net.jobsearchapplication_api.data.service.devicetoken

import net.jobsearchapplication_api.data.models.DeviceToken
import net.jobsearchapplication_api.routes.devicetoken.DeviceTokenParams

interface DeviceTokenService {
	suspend fun getToken(id: String) : DeviceToken?
	suspend fun createToken(id:String,params:DeviceTokenParams) : DeviceToken?
}