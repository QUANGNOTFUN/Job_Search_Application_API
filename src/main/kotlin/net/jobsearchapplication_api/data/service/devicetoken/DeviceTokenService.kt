package net.jobsearchapplication_api.data.service.devicetoken

import net.jobsearchapplication_api.data.models.DeviceToken

interface DeviceTokenService {
	suspend fun getToken(id: String) : DeviceToken?
	suspend fun createToken(id:String,params:String) : DeviceToken?
}