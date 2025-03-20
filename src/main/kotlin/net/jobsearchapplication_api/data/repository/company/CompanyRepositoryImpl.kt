package net.jobsearchapplication_api.data.repository.company

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.company.CompanyService
import net.jobsearchapplication_api.routes.company.CompanyParams
import java.util.*

// CompanyRepositoryImpl.kt
class CompanyRepositoryImpl(
    private val companyService: CompanyService
) : CompanyRepository {
    override suspend fun getAllCompanies(page: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(
            data = companyService.getAllCompanies(page),
            message = SUCCESS
        )
    }

    override suspend fun getCompanyById(id: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun createCompany(params: CompanyParams): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCompany(id: UUID, params: CompanyParams): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompanyJobs(id: UUID): BaseResponse<Any> {
        TODO("Not yet implemented")
    }
    // Implement các phương thức khác...
}