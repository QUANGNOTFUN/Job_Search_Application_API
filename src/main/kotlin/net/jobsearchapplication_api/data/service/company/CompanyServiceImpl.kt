package net.jobsearchapplication_api.data.service.company

import net.jobsearchapplication_api.data.db.schemas.CompanyTable
import net.jobsearchapplication_api.data.models.Company
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.routes.company.CompanyParams
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class CompanyServiceImpl : CompanyService {
    override suspend fun getAllCompanies(page: Int): List<Company> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompanyById(id: UUID): Company? {
        TODO("Not yet implemented")
    }

    override suspend fun createCompany(params: CompanyParams): Company? {
        TODO("Not yet implemented")
    }

    override suspend fun updateCompany(id: UUID, params: CompanyParams): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getCompanyJobs(id: UUID): List<Job> {
        TODO("Not yet implemented")
    }

    // Implement các phương thức khác...
}