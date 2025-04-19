package net.jobSearchApplication_api.data.repository.favorite

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.data.service.favorite.FavoriteService
import java.util.*
import net.jobSearchApplication_api.config.SUCCESS
import net.jobsearchapplication_api.routes.user.FavoriteParams

class FavoriteRepositoryImpl(
    private val postService: FavoriteService
) : FavoriteRepository {

    override suspend fun getSavedJobs(userId: String, page: Int, limit: Int): BaseResponse<Any> {
        return try {
            val savedJobs = postService.getSavedJobs(userId)
            BaseResponse.SuccessResponse(
                data = savedJobs,
                message = SUCCESS
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể lấy danh sách công việc đã lưu")
        }
    }

    override suspend fun saveJob(userId: String, jobId: UUID): BaseResponse<Any> {
        return try {
            val savedJob = postService.saveJob(userId, jobId)
            if (savedJob) {
                BaseResponse.SuccessResponse(
                    data = savedJob,
                    message = "Đã lưu công việc thành công"
                )
            } else {
                BaseResponse.ErrorResponse(message = "Công việc đã được lưu trước đó")
            }
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể lưu công việc")
        }
    }

    override suspend fun unSaveJob(userId: String, jobId: UUID): BaseResponse<Any> {
        return try {
            val result = postService.unSaveJob(userId, jobId)
            if (result) {
                BaseResponse.SuccessResponse(
                    data = null,
                    message = "Đã xóa công việc khỏi danh sách đã lưu"
                )
            } else {
                BaseResponse.ErrorResponse(message = "Không tìm thấy công việc đã lưu")
            }
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể xóa công việc đã lưu")
        }
    }

    override suspend fun isJobSaved(userId: String, jobId: UUID): BaseResponse<Any> {
        return try {
            val isSaved = postService.isJobSaved(userId, jobId)
            BaseResponse.SuccessResponse(
                data = mapOf("isSaved" to isSaved),
                message = SUCCESS
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể kiểm tra trạng thái lưu")
        }
    }

    override suspend fun getSavedJobCount(userId: String): BaseResponse<Any> {
        return try {
            val count = postService.getSavedJobCount(userId)
            BaseResponse.SuccessResponse(
                data = mapOf("count" to count),
                message = SUCCESS
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể đếm số công việc đã lưu")
        }
    }
}