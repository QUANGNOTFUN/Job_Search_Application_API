package net.jobsearchapplication_api.data.service.jobapplication

import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.extensions.toJobApplication
import net.jobsearchapplication_api.data.db.extensions.toUser
import net.jobsearchapplication_api.data.db.schemas.JobApplicationTable
import net.jobsearchapplication_api.data.db.schemas.JobTable
import net.jobsearchapplication_api.data.db.schemas.UserTable
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
        DatabaseFactory.dbQuery {
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
        return DatabaseFactory.dbQuery {
            JobApplicationTable
                .select { JobApplicationTable.userId eq id }
                .map { it.toJobApplication() }
        }
    }

    override suspend fun getJobApplicationsByUserIdAndJobId(userId: String, jobId: UUID): List<User?> {
        return DatabaseFactory.dbQuery {
            JobTable
                .innerJoin(JobApplicationTable, { JobTable.id }, { JobApplicationTable.jobId })
                .innerJoin(UserTable, { JobApplicationTable.userId }, { UserTable.id })
                .select {
                    (JobTable.id eq jobId) and (JobTable.postedBy eq userId)
                }
                .map { it.toUser() }
        }
    }


}
