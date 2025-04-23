package net.jobsearchapplication_api.data.repository.jobcategory

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.db.schemas.JobCategoryTable
import net.jobsearchapplication_api.data.service.jobcategory.JobCategoryService
import net.jobsearchapplication_api.routes.jobcategory.JobCategoryParams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

//  JobCategoryRepositoryImpl.kt
class JobCategoryRepositoryImpl(private val jobCategoryService: JobCategoryService) : JobCategoryRepository {

    override suspend fun getAllCategories(page: Int, limit: Int): BaseResponse<Any> {

        return try {
            BaseResponse.SuccessResponse(
                data = jobCategoryService.getAllJobCategories(page, limit),
                message = SUCCESS
            )
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(
                message = "Error getting jobs category: ${e.localizedMessage}"
            )
        }
    }

    override suspend fun getCategoryById(id: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun createCategory(params: JobCategoryParams): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategory(id: Int, params: JobCategoryParams): Boolean = transaction {
        JobCategoryTable.update({ JobCategoryTable.id eq id }) {
            it[name] = params.name
        } > 0
    }

    override suspend fun deleteCategory(id: Int): Boolean = transaction {
        JobCategoryTable.deleteWhere { JobCategoryTable.id eq id } > 0
    }

    override suspend fun getJobsByCategory(categoryId: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }
    // Implement các phương thức khác...
}