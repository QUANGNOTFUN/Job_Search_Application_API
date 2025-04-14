package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object JobTable : Table("jobs") {

    val id = uuid("id").autoGenerate()
    val title = varchar("title", 255)
    val description = text("description")
    val salaryMin = decimal("salary_min", 10, 2).nullable()
    val salaryMax = decimal("salary_max", 10, 2).nullable()
    val currency = varchar("currency", 10).default("VND")
    val location = varchar("location", 255)
    val jobType = varchar("job_type", 50)
    val experienceLevel = varchar("experience_level", 50)
    val companyId = varchar("company_id", 50).references(CompanyTable.id)
    val postedBy = varchar("posted_by", 50).references(UserTable.id)
    val jobCategory = integer("job_category_id").references(JobCategoryTable.id)
    val benefits = text("benefits").nullable()
    val quantity = integer("quantity").default(1)
    val genderRequire = text("gender_require")
    val deadline = datetime("deadline").nullable()
    val status = varchar("status", 50)
    val requirements = text("requirements").nullable()
    val jobImage = text("job_image").nullable()
    val createdAt = datetime("created_at")


    override val primaryKey = PrimaryKey(id)
}