package net.jobsearchapplication_api.routes.story

data class StoryParams(
    val userId: String,
    val title: String,
    val content: String,
    val isDraft: Boolean
)