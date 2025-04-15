package net.jobsearchapplication_api.data.service.jobapplication

import net.jobSearchApplication_api.data.db.DatabaseFactory
import net.jobSearchApplication_api.data.db.extensions.toJobApplication
import net.jobSearchApplication_api.data.db.schemas.JobApplicationTable
import net.jobSearchApplication_api.data.models.JobApplication
import net.jobSearchApplication_api.data.service.jobapplication.JobApplicationService
import net.jobSearchApplication_api.routes.jobapplication.JobApplicationParams
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime
import java.util.*

class JobApplicationServiceImpl: JobApplicationService {

    override suspend fun createJobApplication(params: JobApplicationParams): JobApplication? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = JobApplicationTable.insert {
                it[userId] = params.userId
                it[jobId] = params.jobId
                it[status] = params.status
                it[coverLetter] = params.coverLetter
                it[cvUrl] = params.cvUrl
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


}