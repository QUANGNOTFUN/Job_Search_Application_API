package net.jobsearchapplication_api.data.models

import java.util.*

data class JobCategory(
    val id: UUID = UUID.randomUUID(),
    val name: String
)
data class JobCategoryMapping(
    val jobId: UUID,
    val categoryId: UUID

)