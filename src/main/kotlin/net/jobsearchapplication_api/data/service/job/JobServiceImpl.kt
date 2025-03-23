package net.jobsearchapplication_api.data.service.job

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.extensions.*
import net.jobsearchapplication_api.data.db.schemas.*
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.data.models.common.PaginatedResult
import net.jobsearchapplication_api.routes.job.JobParams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class JobServiceImpl : JobService {
    override suspend fun getJobsByCompanyId(companyId: UUID): List<Job> {
//        var pageCount: Long = 0
//        var nextPage: Long? = null
//
//        val stories = DatabaseFactory.dbQuery {
//            StoryTable
//                .innerJoin(UserTable, { UserTable.id }, { CompanyTable.userId })
//                .select {
//                    (UserTable.userId eq userId) and (CompanyTable.isDraft eq isDraft)
//                }.orderBy(StoryTable.createdAt, SortOrder.DESC).also {
//                    pageCount = it.count() / limit
//                    if (page < pageCount)
//                        nextPage = page + 1L
//                }.limit(limit, (limit * page).toLong())
//                .mapNotNull { it.toStoryJoinedWithUser() }
//        }
//        return PaginatedResult(pageCount, nextPage, stories)

        TODO("Not yet implemented")

    }

    override suspend fun getAllJobs(page: Int, limit: Int): PaginatedResult<Job> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val jobs = DatabaseFactory.dbQuery {
            // 1. Đếm tổng số jobs
            val totalJobs = JobTable.selectAll().count()
            pageCount = (totalJobs + limit - 1) / limit

            // 2. Tính nextPage
            if (page < pageCount) {
                nextPage = page + 1L
            }

            // 3. Query với joins và chuyển thành List<Job> không null
            JobTable
                .selectAll()
                .orderBy(JobTable.createdAt, SortOrder.DESC)
                .limit(limit, (page - 1) * limit.toLong())
                .mapNotNull { row -> // Sử dụng mapNotNull thay vì map
                    row.toJob()
                }
        }

        // 4. Trả về kết quả với List<Job> không null
        return PaginatedResult(
            pageCount = pageCount,
            nextPage = nextPage,
            data = jobs.filterNotNull() // Đảm bảo không có phần tử null
        )
    }

    override suspend fun getJobById(id: UUID): Job? {
        val jobRow = DatabaseFactory.dbQuery {  JobTable.select { JobTable.id eq id }.first() }
        return jobRow.toJob()
    }

    override suspend fun getJobsByUserId(id: UUID): Job? {
        TODO("Not yet implemented")
    }


    override suspend fun createJob(params: JobParams): Job? {
            var statement: InsertStatement<Number>? = null
            DatabaseFactory.dbQuery {
                statement = JobTable.insert {
                    // Thông tin cơ bản
                    it[title] = params.title
                    it[description] = params.description
                    it[companyId] = params.companyId
                    it[postedBy] = params.postedBy
                    // Lương
                    it[salaryMin] = BigDecimal(params.salary.min)
                    it[salaryMax] = BigDecimal(params.salary.max)
                    it[currency] = params.salary.currency

                    // Địa điểm và loại công việc
                    it[location] = params.location.city
                    it[jobType] = params.employmentType
                    it[experienceLevel] = params.experience.level

                    // Yêu cầu và quyền lợi
                    it[requirements] = params.requirements
                    it[benefits] = params.benefits

                    // Thông tin tuyển dụng
                    it[quantity] = params.positionsAvailable
                    it[genderRequire] = params.genderRequirement
                    it[deadline] = params.deadline
                    it[status] = params.status

                    // Hình ảnh job (nếu có)
                    it[jobImage] = params.additionalInfo.jobImage

                    // Metadata
                    it[createdAt] = LocalDateTime.now()
                }
            }
        return statement?.resultedValues?.get(0)?.toJob()
    }

    override suspend fun updateJob(id: UUID, params: JobParams): BaseResponse<Job> {
        TODO("Not yet implemented")

//        return try {
//            // Kiểm tra job có tồn tại không
//            val existingJob = JobService.getJobById(id)
//            if (existingJob == null) {
//                return BaseResponse.ErrorResponse(
//                    message = "Job not found"
//                )
//            }
//
//            // Validate input
//            val validationErrors = validateJobParams(params)
//            if (validationErrors.isNotEmpty()) {
//                return BaseResponse.ErrorResponse(
//                    message = "Validation failed",
//                    errors = validationErrors
//                )
//            }
//
//            // Thực hiện update
//            val success = jobService.updateJob(id, params)
//            if (success) {
//                // Lấy job đã update để trả về
//                val updatedJob = jobService.getJobById(id)
//                BaseResponse.SuccessResponse(
//                    data = updatedJob,
//                    message = "Job updated successfully"
//                )
//            } else {
//                BaseResponse.ErrorResponse(
//                    message = "Failed to update job"
//                )
//            }
//        } catch (e: Exception) {
//            BaseResponse.ErrorResponse(
//                message = "Error updating job: ${e.localizedMessage}"
//            )
//        }
    }

    // Hàm validate input
    private fun validateJobParams(params: JobParams): List<String> {
        val errors = mutableListOf<String>()

        // Validate title
        if (params.title.isBlank()) {
            errors.add("Title is required")
        }
        if (params.title.length < 10) {
            errors.add("Title must be at least 10 characters")
        }

        // Validate description
        if (params.description.isBlank()) {
            errors.add("Description is required")
        }

        // Validate salary
        if (params.salary.min > params.salary.max) {
            errors.add("Minimum salary cannot be greater than maximum salary")
        }

        // Validate deadline
        if (params.deadline?.isBefore(LocalDateTime.now()) == true) {
            errors.add("Deadline must be in the future")
        }

        return errors
    }

    override suspend fun deleteJob(id: UUID): Boolean {
        var result = -1
        DatabaseFactory.dbQuery {
            result = JobTable.deleteWhere { JobTable.id eq id }
        }
        return result == 1
   }

    override suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): List<Job> {
        TODO("Not yet implemented")
    }
}