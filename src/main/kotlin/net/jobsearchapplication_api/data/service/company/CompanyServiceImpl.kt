package net.jobsearchapplication_api.data.repository.company

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toCompany
import net.jobsearchapplication_api.data.db.schemas.CompanyTable
import net.jobsearchapplication_api.data.models.Company
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.data.service.company.CompanyService
import net.jobsearchapplication_api.routes.company.CompanyParams
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime
import java.util.UUID

class CompanyServiceImpl : CompanyService {
    override suspend fun getAllCompanies(page: Int, limit: Int): List<Company> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompanyById(id: UUID): Company? {
        return dbQuery {
            CompanyTable.select { CompanyTable.id eq id }.firstOrNull()?.let {
                Company(
                    id = it[CompanyTable.id],
                    name = it[CompanyTable.name],
                    description = it[CompanyTable.description],
                    location = it[CompanyTable.location],
                    website = it[CompanyTable.website],
                    logo = it[CompanyTable.logo],
                    size = it[CompanyTable.size],
                    userId = it[CompanyTable.userId],
                    createdAt = it[CompanyTable.createdAt]
                )
            }
        }
    }


    override suspend fun createCompany(params: CompanyParams): Company? {
        val statement = dbQuery {
            CompanyTable.insert {
                it[id] = UUID.randomUUID()
                it[name] = params.name
                it[description] = params.description
                it[location] = params.location
                it[website] = params.website
                it[logo] = params.logo
                it[size] = params.size
                it[createdAt] = LocalDateTime.now()
                it[userId] = params.userId
            }
        }

        return statement.resultedValues?.firstOrNull()?.toCompany()
            ?: throw IllegalStateException("Failed to create company")
    }

    override suspend fun updateCompany(id: UUID, params: CompanyParams): Company? {
        val updated = dbQuery {
            CompanyTable.update({ CompanyTable.id eq id }) {
                it[name] = params.name
                if (params.description != null) it[description] = params.description
                if (params.location != null) it[location] = params.location
                if (params.website != null) it[website] = params.website
                if (params.logo != null) it[logo] = params.logo
                if (params.size != null) it[size] = params.size
            }
        }

        // Nếu `updated > 0` thì thực hiện truy vấn lại để lấy dữ liệu cập nhật
        if (updated > 0) {
            return dbQuery {
                CompanyTable.select { CompanyTable.id eq id }
                    .map { it.toCompany() }
                    .firstOrNull()
            }
        }

        throw IllegalStateException("Failed to update company")
    }


    override suspend fun deleteCompany(id: UUID): Boolean {
        return try {
            dbQuery {
                val deleteCount = CompanyTable.deleteWhere { CompanyTable.id eq id }
                deleteCount > 0
            }
        } catch (e: Exception) {
            false // Hoặc ném ngoại lệ tùy yêu cầu
        }
    }

    override suspend fun getCompanyJobs(id: UUID): List<Job> {
        TODO("Not yet implemented")
    }

}