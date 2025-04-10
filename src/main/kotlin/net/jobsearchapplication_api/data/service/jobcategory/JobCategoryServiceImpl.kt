package net.jobsearchapplication_api.data.service.jobcategory

import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.extensions.toJobCategory
import net.jobsearchapplication_api.data.db.schemas.JobCategoryTable
import net.jobsearchapplication_api.data.models.JobCategory
import net.jobsearchapplication_api.data.models.common.PaginatedResult
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.selectAll

class JobCategoryServiceImpl : JobCategoryService {

    override suspend fun getAllJobCategories(page: Int, limit: Int): PaginatedResult<JobCategory> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val categories = DatabaseFactory.dbQuery {
            val totalJobCategory = JobCategoryTable.selectAll().count()
            pageCount = (totalJobCategory + limit - 1) / limit

        // 2. Tính nextPage
        if (page < pageCount) {
            nextPage = page + 1L
        }

        // 3. Query với joins và chuyển thành List<Job> không null
        JobCategoryTable
            .selectAll()
            .orderBy(JobCategoryTable.createdAt, SortOrder.DESC)
            .limit(limit, (page - 1) * limit.toLong())
            .mapNotNull { row -> // Sử dụng mapNotNull thay vì map
                row.toJobCategory()
            }
        }

        // 4. Trả về kết quả với List<JobCategory> không null
        return PaginatedResult(
            pageCount = pageCount,
            nextPage = nextPage,
            data = categories
        )
    }

    override suspend fun getCategoryById(id: Int): JobCategory? {
        TODO("Not yet implemented")
    }

    override suspend fun createCategory(name: String): JobCategory? {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategory(id: Int, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCategory(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

// Implement các phương thức khác...
