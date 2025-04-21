package net.jobsearchapplication_api.data.models

data class Comment(
    val id: Int,
    val userId: String,
    val storyId: Int,
    val comment: String,
    val createdAt: String
)