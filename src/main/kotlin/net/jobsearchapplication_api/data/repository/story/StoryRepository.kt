package net.jobsearchapplication_api.data.repository.story

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.story.StoryParams
import java.util.*

interface StoryRepository {
    suspend fun getMyStories(userId: UUID, page: Int, limit: Int, isDraft: Boolean): BaseResponse<Any>
    suspend fun getAllStories(page: Int, limit: Int): BaseResponse<Any>
    suspend fun add(storyParams: StoryParams): BaseResponse<Any>
    suspend fun get(id: Int): BaseResponse<Any>
    suspend fun update(id: Int, storyParams: StoryParams): BaseResponse<Any>
    suspend fun delete(storyId: Int): BaseResponse<Any>
    suspend fun like(userId: UUID, storyId: Int): BaseResponse<Any>
    suspend fun comment(userId: UUID, storyId: Int, comment: String): BaseResponse<Any>
    suspend fun getComments(storyId: Int): BaseResponse<Any>
}