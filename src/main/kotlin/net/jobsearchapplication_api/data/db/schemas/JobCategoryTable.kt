package net.jobsearchapplication_api.data.db.schemas

import net.jobsearchapplication_api.data.db.schemas.JobTable.nullable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object JobCategoryTable : Table("job_categories") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255).uniqueIndex()
    val jobCategoryImage = text("job_category_image").nullable()
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id)
}