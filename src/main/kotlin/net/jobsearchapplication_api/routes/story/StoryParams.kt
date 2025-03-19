package net.jobsearchapplication_api.routes.story

data class StoryParams(
    val userId: Int,
    val title: String,
    val content: String,
    val isDraft: Boolean
)