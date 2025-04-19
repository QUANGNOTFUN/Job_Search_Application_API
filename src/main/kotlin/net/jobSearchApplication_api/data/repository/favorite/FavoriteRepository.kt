package net.jobSearchApplication_api.data.repository.favorite

import net.jobSearchApplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.user.FavoriteParams
import java.util.*

interface FavoriteRepository {
    suspend fun getSavedJobs(userId: String, page: Int, limit: Int): BaseResponse<Any>
    suspend fun saveJob(userId: String, jobId: UUID): BaseResponse<Any>
    suspend fun unSaveJob(userId: String, jobId: UUID): BaseResponse<Any>
    suspend fun isJobSaved(userId: String, jobId: UUID): BaseResponse<Any>
    suspend fun getSavedJobCount(userId: String): BaseResponse<Any>

}

