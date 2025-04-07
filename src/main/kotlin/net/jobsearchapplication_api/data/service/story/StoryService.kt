package net.jobsearchapplication_api.data.service.story

import net.jobsearchapplication_api.data.models.Comment
import net.jobsearchapplication_api.data.models.Story
import net.jobsearchapplication_api.data.models.common.PaginatedResult
import net.jobsearchapplication_api.routes.story.StoryParams

interface StoryService {
    suspend fun get(id: Int): Story?
    suspend fun getMyStories(userId: String, page: Int, limit: Int, isDraft: Boolean): PaginatedResult<Story>
    suspend fun getAllStories(page: Int, limit: Int): PaginatedResult<Story>
    suspend fun getLikedStories(userId: String): List<Story>
    suspend fun add(storyParams: StoryParams): Story?
    suspend fun update(id: Int, storyParams: StoryParams): Boolean
    suspend fun delete(storyId: Int): Boolean
    suspend fun like(userId: String, storyId: Int): Boolean
    suspend fun comment(userId: String, storyId: Int, comment: String): Boolean
    suspend fun getComments(storyId: Int): List<Comment>
}