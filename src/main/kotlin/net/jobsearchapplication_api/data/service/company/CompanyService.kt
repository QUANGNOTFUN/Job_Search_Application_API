package net.jobsearchapplication_api.data.service.company

import net.jobsearchapplication_api.data.models.Company
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.routes.company.CompanyParams

interface CompanyService {
    suspend fun getAllCompanies(page: Int, limit :Int): List<Company>
    suspend fun getCompanyById(id: String): Company?
    suspend fun createCompany(params: CompanyParams): Company?
    suspend fun updateCompany(id: String, params: CompanyParams): Company?
    suspend fun deleteCompany(id: String): Boolean
    suspend fun getCompanyJobs(id: String): List<Job?>
}
