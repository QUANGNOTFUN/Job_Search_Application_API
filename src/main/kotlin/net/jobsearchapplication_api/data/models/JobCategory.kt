package net.jobsearchapplication_api.data.models

import java.util.*

data class JobCategoryRepository (
    val id: UUID = UUID.randomUUID(),
    val name: String
)
data class JobCategoryMappingRepository(
    val jobId: UUID,
    val categoryId: UUID

)