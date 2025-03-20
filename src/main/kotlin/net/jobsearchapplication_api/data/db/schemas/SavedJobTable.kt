package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object SavedJobTable : Table("saved_jobs") {
    val id = uuid("id").autoGenerate()
    val userId = uuid("user_id").references(UserTable.id)
    val jobId = uuid("job_id").references(JobTable.id)
    val savedAt = datetime("saved_at")
    override val primaryKey = PrimaryKey(id)

}