package net.jobsearchapplication_api.data.repository.jobcategory

import net.jobsearchapplication_api.base.BaseResponse
import java.util.*

// JobCategoryRepository.kt
interface JobCategoryRepository {
    suspend fun getAllCategories(): BaseResponse<Any>
    suspend fun getCategoryById(id: UUID): BaseResponse<Any>
    suspend fun createCategory(name: String): BaseResponse<Any>
    suspend fun updateCategory(id: UUID, name: String): BaseResponse<Any>
    suspend fun deleteCategory(id: UUID): BaseResponse<Any>
    suspend fun getJobsByCategory(categoryId: UUID): BaseResponse<Any>
}
