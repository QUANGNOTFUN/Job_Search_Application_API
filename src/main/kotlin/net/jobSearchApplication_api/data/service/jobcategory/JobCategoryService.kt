package net.jobSearchApplication_api.data.service.jobcategory

import net.jobSearchApplication_api.data.models.JobCategory
import net.jobSearchApplication_api.data.models.common.PaginatedResult

interface JobCategoryService {
    suspend fun getAllJobCategories(page: Int, limit: Int): PaginatedResult<JobCategory>
    suspend fun getCategoryById(id: Int): JobCategory?
    suspend fun createCategory(name: String): JobCategory?
    suspend fun updateCategory(id: Int, name: String): Boolean
    suspend fun deleteCategory(id: Int): Boolean
}