package net.jobsearchapplication_api.data.repository.savejob

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.service.savedjob.SavedJobService
import java.util.*
import net.jobsearchapplication_api.config.SUCCESS

class SavedJobRepositoryImpl(private val savedJobService: SavedJobService ) : SavedJobRepository {

    override suspend fun getSavedJobs(userId: UUID, page: Int, limit: Int): BaseResponse<Any> {
        return try {
            val savedJobs = savedJobService.getSavedJobs(userId)
            BaseResponse.SuccessResponse(
                data = savedJobs,
                message = SUCCESS
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể lấy danh sách công việc đã lưu")
        }
    }

    override suspend fun saveJob(userId: UUID, jobId: UUID): BaseResponse<Any> {
        return try {
            val savedJob = savedJobService.saveJob(userId, jobId)
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

    override suspend fun unsaveJob(userId: UUID, jobId: UUID): BaseResponse<Any> {
        return try {
            val result = savedJobService.unsaveJob(userId, jobId)
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

    override suspend fun isJobSaved(userId: UUID, jobId: UUID): BaseResponse<Any> {
        return try {
            val isSaved = savedJobService.isJobSaved(userId, jobId)
            BaseResponse.SuccessResponse(
                data = mapOf("isSaved" to isSaved),
                message = SUCCESS
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể kiểm tra trạng thái lưu")
        }
    }

    override suspend fun getSavedJobCount(userId: UUID): BaseResponse<Any> {
        return try {
            val count = savedJobService.getSavedJobCount(userId)
            BaseResponse.SuccessResponse(
                data = mapOf("count" to count),
                message = SUCCESS
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Không thể đếm số công việc đã lưu")
        }
    }
}