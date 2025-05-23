package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object JobApplicationTable: Table("applications"){
    val userId = varchar("user_id", 36).references(UserTable.id)
    val jobId = uuid("job_id").references(JobTable.id)
    val createdAt = datetime("created_at")
    val status = varchar("status", 255)
    val coverLetter = text("cover_letter")
    val additionalInfo = text("additional_info")
    val cvUrl = text("cv")
    override val primaryKey = PrimaryKey(userId, jobId)
}