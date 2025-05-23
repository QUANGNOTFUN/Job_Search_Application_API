package net.jobsearchapplication_api.data.service.job

import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.*
import net.jobsearchapplication_api.data.db.schemas.*
import net.jobsearchapplication_api.data.models.AppliedJob
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

    override suspend fun getJobsByCategory(cateId: Int): List<Job> {
        return dbQuery {
            JobTable.select { JobTable.jobCategory eq cateId }
                .mapNotNull { resultRow ->
                    resultRow.toJob()
                }
        }
    }

    override suspend fun getAppliedJobs(userId: String): List<AppliedJob> {
        return dbQuery {
            (JobApplicationTable innerJoin JobTable)
                .select { JobApplicationTable.userId eq userId }
                .map { row ->
                    val job = row.toJob()
                    val jobApplication = row.toJobApplication()

                    AppliedJob(
                        id = job!!.id,
                        title = job.title,
                        salaryMin = job.salaryMin,
                        salaryMax = job.salaryMax,
                        location = job.location,
                        jobImage = job.jobImage,
                        statusResponse = jobApplication!!.status,
                        createdAt = jobApplication.createdAt
                    )
                }
        }
    }

    override suspend fun getFavoriteJobs(userId: String): List<Job?> {
        return dbQuery {
            // Lấy favoriteJobPosting từ UserTable
            val favoriteJobIds = UserTable
                .select { UserTable.id eq userId }.firstOrNull()
                ?.get(UserTable.favoriteJobPosting)
                ?.split(",")
                ?.mapNotNull { id ->
                    UUID.fromString(id.trim())
                } ?: emptyList()

            if (favoriteJobIds.isEmpty()) {
                return@dbQuery emptyList()
            }

            JobTable.select { JobTable.id inList favoriteJobIds }
                .map { resultRow -> resultRow.toJob() }
        }
    }

    override suspend fun getPostedJobs(userId: String): List<Job?> {
        return dbQuery {
            JobTable.select { JobTable.postedBy eq userId }
                .map { resultRow ->
                    resultRow.toJob()
                }
        }
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
            data = jobs // Đảm bảo không có phần tử null
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
                    it[jobCategory] = params.categoryId

                    // Lương
                    it[salaryMin] = BigDecimal(params.salaryMin)
                    it[salaryMax] = BigDecimal(params.salaryMax)
                    it[currency] = params.currency
                    it[salaryPeriod] = params.salaryPeriod

                    // Địa điểm và loại công việc
                    it[location] = params.location
                    it[jobType] = params.employmentType
                    it[experienceLevel] = params.experienceLevel

                    // Yêu cầu và quyền lợi
                    it[requirements] = params.requirements
                    it[benefits] = params.benefits

                    // Thông tin tuyển dụng
                    it[quantity] = params.positionsAvailable
                    it[genderRequire] = params.genderRequirement
                    it[deadline] = params.deadline
                    it[status] = params.status

                    // Hình ảnh job (nếu có)
                    it[jobImage] = params.additionalInfo?.jobImage

                    // Metadata
                    it[createdAt] = LocalDateTime.now()
                }
            }
        return statement?.resultedValues?.get(0)?.toJob()
    }

    override suspend fun updateJob(id: UUID, params: JobParams): Job? {
        return try {
            DatabaseFactory.dbQuery {
                JobTable.update({ JobTable.id eq id }) { table ->
                    // Thông tin cơ bản
                    table[title] = params.title
                    table[description] = params.description
                    table[companyId] = params.companyId
                    table[postedBy] = params.postedBy

                    // Thông tin lương
                    table[salaryMin] = BigDecimal(params.salaryMin.toString())
                    table[salaryMax] = BigDecimal(params.salaryMax.toString())
                    table[currency] = params.currency
					table[salaryPeriod] = params.salaryPeriod

                    // Địa điểm và loại công việc
                    table[location] = params.location
                    table[jobType] = params.employmentType
                    table[experienceLevel] = params.experienceLevel

                    // Yêu cầu và quyền lợi
                    table[requirements] = params.requirements
                    table[benefits] = params.benefits

                    // Thông tin tuyển dụng
                    table[quantity] = params.positionsAvailable
                    table[genderRequire] = params.genderRequirement
                    table[deadline] = params.deadline
                    table[status] = params.status

                    // Hình ảnh job
                    params.additionalInfo?.jobImage?.let {
                        table[jobImage] = it
                    }
                }

                // Trả về job đã update
                JobTable
                    .select { JobTable.id eq id }
                    .firstOrNull()
                    ?.toJob()
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteJob(id: UUID): Boolean {
        var result = -1
        DatabaseFactory.dbQuery {
            result = JobTable.deleteWhere { JobTable.id eq  id }
        }
        return result == 1
   }

	override suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): PaginatedResult<Job> {
		var pageCount: Long = 0
		var nextPage: Long? = null
		val jobs = dbQuery {
			try {
				val limit = 20
				val offset = (page - 1) * limit.toLong()
				val totalJobs = JobTable.selectAll().count()
				pageCount = (totalJobs + limit - 1) / limit
				if (page < pageCount) {
					nextPage = page + 1L
				}
				JobTable.selectAll()
					.orderBy(JobTable.createdAt, SortOrder.DESC)
					.limit(limit, offset)
					.mapNotNull { it.toJob() }
			} catch (e: Exception) {
				println("Error in searchJobs: ${e.message}")
				emptyList()
			}
		}
		return PaginatedResult(pageCount, nextPage, jobs)
	}
}