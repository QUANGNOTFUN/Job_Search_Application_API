package net.jobsearchapplication_api.data.service.jobapplication

import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toAppliedUserWithApplication
import net.jobsearchapplication_api.data.db.extensions.toJobApplication
import net.jobsearchapplication_api.data.db.extensions.toUser
import net.jobsearchapplication_api.data.db.schemas.JobApplicationTable
import net.jobsearchapplication_api.data.db.schemas.JobTable
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.AppliedJob
import net.jobsearchapplication_api.data.models.JobApplication
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.jobapplication.JobApplicationParams
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.innerJoin
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
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
