package net.jobsearchapplication_api.data.service.jobapplication

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toAppliedUserWithApplication
import net.jobsearchapplication_api.data.db.extensions.toJobApplication
import net.jobsearchapplication_api.data.db.schemas.JobApplicationTable
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.JobApplication
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime
import java.util.UUID

class JobApplicationServiceImpl : JobApplicationService {

    override suspend fun createJobApplication(params: JobApplicationParams): JobApplication? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = JobApplicationTable.insert {
                it[userId] = params.userId
                it[jobId] = params.jobId
                it[status] = params.status
                it[coverLetter] = params.coverLetter
                it[cvUrl] = params.cvUrl
                it[additionalInfo] = params.additionalInfo
                it[createdAt] = LocalDateTime.now()
            }
        }
        return statement?.resultedValues?.get(0)?.toJobApplication()
    }

    override suspend fun getJobApplicationsByUserId(id: String): List<JobApplication?> {
        return dbQuery {
            JobApplicationTable
                .select { JobApplicationTable.userId eq id }
                .map { it.toJobApplication() }
        }
    }

    override suspend fun updateStatusAppliedJob(userId: String, jobId: UUID, status: String): BaseResponse<Any> {
        return try {
            val updatedRows = dbQuery {
                JobApplicationTable.update(
                    where = { (JobApplicationTable.userId eq userId) and (JobApplicationTable.jobId eq jobId) }
                ) { it[this.status] = status }
            }

            if (updatedRows > 0) {
                BaseResponse.SuccessResponse(data = Any(), message = SUCCESS)
            } else {
                BaseResponse.ErrorResponse(message = "Không tìm thấy ứng tuyển với userId: $userId và jobId: $jobId", statusCode = HttpStatusCode.NotFound)
            }
        } catch (e: Exception) {
            BaseResponse.ErrorResponse(message = "Lỗi khi cập nhật trạng thái ứng tuyển: ${e.message}",
                statusCode = HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun getAppliedUsersByJobId(jobId: UUID): List<Any?> {
        return dbQuery {
            (JobApplicationTable innerJoin UserTable)
                .select { JobApplicationTable.jobId eq jobId }
                .map { resultRow ->
                    resultRow.toAppliedUserWithApplication()
                }
        }
    }
}
