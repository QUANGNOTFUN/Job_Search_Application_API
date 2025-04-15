package net.jobSearchApplication_api.data.repository.story

import net.jobSearchApplication_api.base.BaseResponse
import net.jobSearchApplication_api.routes.story.StoryParams

interface StoryRepository {
    suspend fun getMyStories(userId: String, page: Int, limit: Int, isDraft: Boolean): BaseResponse<Any>
    suspend fun getAllStories(page: Int, limit: Int): BaseResponse<Any>
    suspend fun add(storyParams: StoryParams): BaseResponse<Any>
    suspend fun get(id: Int): BaseResponse<Any>
    suspend fun update(id: Int, storyParams: StoryParams): BaseResponse<Any>
    suspend fun delete(storyId: Int): BaseResponse<Any>
    suspend fun like(userId: String, storyId: Int): BaseResponse<Any>
    suspend fun comment(userId: String, storyId: Int, comment: String): BaseResponse<Any>
    suspend fun getComments(storyId: Int): BaseResponse<Any>
}