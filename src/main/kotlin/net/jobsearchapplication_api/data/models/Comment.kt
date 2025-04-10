package net.jobsearchapplication_api.data.models

import java.util.UUID

data class Comment(
    val id: Int,
    val userId: String,
    val storyId: Int,
    val comment: String,
    val createdAt: String
)