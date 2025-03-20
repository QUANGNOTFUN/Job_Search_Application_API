package net.jobsearchapplication_api.data.repository.jobcategory

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.jobcategory.JobCategoryService
import java.util.*

// JobCategoryRepositoryImpl.kt
class JobCategoryRepositoryImpl(
    private val categoryService: JobCategoryService
) : JobCategoryRepository {
    override suspend fun getAllCategories(): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(
            data = categoryService.getAllCategories(),
            message = SUCCESS
        )
    }

    override suspend fun getCategoryById(id: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun createCategory(name: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategory(id: UUID, name: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCategory(id: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun getJobsByCategory(categoryId: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }
    // Implement các phương thức khác...
}