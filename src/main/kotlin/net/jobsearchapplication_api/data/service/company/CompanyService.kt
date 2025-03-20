package net.jobsearchapplication_api.data.service.company

import net.jobsearchapplication_api.data.models.Company
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.routes.company.CompanyParams
import java.util.*

interface CompanyService {
    suspend fun getAllCompanies(page: Int): List<Company>
    suspend fun getCompanyById(id: UUID): Company?
    suspend fun createCompany(params: CompanyParams): Company?
    suspend fun updateCompany(id: UUID, params: CompanyParams): Boolean
    suspend fun getCompanyJobs(id: UUID): List<Job>
}

