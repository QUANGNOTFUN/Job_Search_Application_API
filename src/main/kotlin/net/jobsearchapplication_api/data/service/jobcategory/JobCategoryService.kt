package net.jobsearchapplication_api.data.service.jobcategory

import net.jobsearchapplication_api.data.models.JobCategory
import java.util.*

interface JobCategoryService {
    suspend fun getAllCategories(): List<JobCategory>
    suspend fun getCategoryById(id: UUID): JobCategory?
    suspend fun createCategory(name: String): JobCategory?
    suspend fun updateCategory(id: UUID, name: String): Boolean
    suspend fun deleteCategory(id: UUID): Boolean
}