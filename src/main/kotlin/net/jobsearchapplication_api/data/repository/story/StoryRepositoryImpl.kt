package net.jobsearchapplication_api.data.repository.story

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.GENERIC_ERROR
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.story.StoryService
import net.jobsearchapplication_api.routes.story.StoryParams

class StoryRepositoryImpl(
    private val storyService: StoryService
) : StoryRepository {
    override suspend fun getMyStories(userId: String, page: Int, limit: Int, isDraft: Boolean): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = storyService.getMyStories(userId, page, limit, isDraft), message = SUCCESS)
    }

    override suspend fun getAllStories(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = storyService.getAllStories(page, limit), message = SUCCESS)
    }

    override suspend fun add(storyParams: StoryParams): BaseResponse<Any> {
        val story = storyService.add(storyParams)
        return if (story != null) {
            BaseResponse.SuccessResponse(data = story, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(statusCode = HttpStatusCode.NotFound, message = GENERIC_ERROR)
        }
    }

    override suspend fun get(id: Int): BaseResponse<Any> {
        val story = storyService.get(id)
        return if (story != null) {
            BaseResponse.SuccessResponse(data = story, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = GENERIC_ERROR)
        }
    }

    override suspend fun update(id: Int, storyParams: StoryParams): BaseResponse<Any> {
        if (storyService.update(id, storyParams)) {
            return BaseResponse.SuccessResponse(data = storyService.get(id), message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun delete(storyId: Int): BaseResponse<Any> {
        if (storyService.delete(storyId)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun like(userId: String, storyId: Int): BaseResponse<Any> {
        if (storyService.like(userId, storyId)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun comment(userId: String, storyId: Int, comment: String): BaseResponse<Any> {
        if (storyService.comment(userId, storyId, comment)) {
            return BaseResponse.SuccessResponse(data = null, message = SUCCESS)
        }
        return BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun getComments(storyId: Int): BaseResponse<Any> {
        val comments = storyService.getComments(storyId)
        return BaseResponse.SuccessResponse(data = storyService.getComments(storyId), message = SUCCESS)
    }
}