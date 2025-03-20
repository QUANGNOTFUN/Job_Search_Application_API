package net.jobsearchapplication_api.data.models

import java.util.UUID

data class Comment(
    val id: Int,
    val userId: UUID,
    val storyId: Int,
    val comment: String,
    val createdAt: String
)