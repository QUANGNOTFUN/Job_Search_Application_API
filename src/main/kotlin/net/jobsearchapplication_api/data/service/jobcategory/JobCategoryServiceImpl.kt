package net.jobsearchapplication_api.data.service.jobcategory

import net.jobsearchapplication_api.data.db.schemas.JobCategoryTable
import net.jobsearchapplication_api.data.models.JobCategory
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

//class JobCategoryServiceImpl : JobCategoryService {
//    override suspend fun getAllCategories(): List<JobCategory> {
//        return transaction {
//            JobCategoryTable
//                .selectAll()
//                .map { it.toJobCategory() }
//        }
//    }
//
//// Implement các phương thức khác...
