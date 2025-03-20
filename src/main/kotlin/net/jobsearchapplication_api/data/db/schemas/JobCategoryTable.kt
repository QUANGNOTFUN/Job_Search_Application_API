package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table

object JobCategoryTable : Table("job_categories") {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", 255).uniqueIndex()
    override val primaryKey = PrimaryKey(CompanyTable.id)

}