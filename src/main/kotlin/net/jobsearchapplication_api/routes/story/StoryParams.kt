package net.jobsearchapplication_api.routes.story

import java.util.UUID

data class StoryParams(
    val userId: UUID,
    val title: String,
    val content: String,
    val isDraft: Boolean
)