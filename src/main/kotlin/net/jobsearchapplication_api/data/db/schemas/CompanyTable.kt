package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object CompanyTable : Table("companies") {
    val id = varchar("user_id", 50)
    val name = varchar("name", 255)
    val description = text("description").nullable()
    val location = varchar("location", 255).nullable()
    val website = varchar("website", 255).nullable()
    val logo = text("logo").nullable()
    val size = integer("size").nullable()
    val createdAt = datetime("created_at")
    val userId = varchar("user_id", 50).references(UserTable.id).nullable()

    override val primaryKey = PrimaryKey(id)
}

