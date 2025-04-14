package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table

object JobCategoryMappingTable : Table("job_category_mapping") {
    val jobId = uuid("job_id").references(JobTable.id)
    val categoryId = integer("category_id").references(JobCategoryTable.id)
    override val primaryKey = PrimaryKey(jobId, categoryId)
}