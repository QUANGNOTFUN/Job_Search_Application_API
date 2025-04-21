package net.jobsearchapplication_api.data.service.jobcategory

import net.jobsearchapplication_api.data.models.JobCategory
import net.jobsearchapplication_api.data.models.common.PaginatedResult
import net.jobsearchapplication_api.routes.jobcategory.JobCategoryParams

interface JobCategoryService {
    suspend fun getAllJobCategories(page: Int, limit: Int): PaginatedResult<JobCategory>
    suspend fun getCategoryById(id: Int): JobCategory?
    suspend fun createCategory(params: JobCategoryParams): JobCategory?
    suspend fun updateCategory(id: Int, name: String): Boolean
    suspend fun deleteCategory(id: Int): Boolean
}