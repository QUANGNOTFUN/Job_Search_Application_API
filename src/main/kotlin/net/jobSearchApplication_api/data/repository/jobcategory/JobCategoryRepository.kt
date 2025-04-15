package net.jobSearchApplication_api.data.repository.jobcategory

import net.jobSearchApplication_api.base.BaseResponse
import java.util.*
import net.jobSearchApplication_api.routes.jobcategory.JobCategoryParams

// JobCategoryRepository.kt
interface JobCategoryRepository {
    suspend fun getAllCategories(page: Int, limit: Int): BaseResponse<Any>
    suspend fun getCategoryById(id: UUID): BaseResponse<Any>
    suspend fun createCategory(params: JobCategoryParams): BaseResponse<Any>
    suspend fun updateCategory(id: Int, params: JobCategoryParams): Boolean
    suspend fun deleteCategory(id: Int): Boolean
    suspend fun getJobsByCategory(categoryId: UUID): BaseResponse<Any>
}
