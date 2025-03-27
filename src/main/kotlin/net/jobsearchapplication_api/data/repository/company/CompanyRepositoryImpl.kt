package net.jobsearchapplication_api.data.repository.company

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.ERROR_CREATE_COMPANY
import net.jobsearchapplication_api.config.SUCCESS
import net.jobsearchapplication_api.data.service.company.CompanyService
import net.jobsearchapplication_api.routes.company.CompanyParams
import java.util.*

// CompanyRepositoryImpl.kt
class CompanyRepositoryImpl (
    private val companyService: CompanyService
) : CompanyRepository {
    override suspend fun getAllCompanies(page: Int, limit: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(
            data = companyService.getAllCompanies(page, limit),
            message = SUCCESS
        )
    }

    override suspend fun getCompanyById(id: UUID): BaseResponse<Any> {
        val companyId = companyService.getCompanyById(id)
        return BaseResponse.SuccessResponse(
            data = companyId,
            message = SUCCESS
        )
    }

    override suspend fun createCompany(params: CompanyParams): BaseResponse<Any> {
        val company = companyService.createCompany(params)

        val response = if (company != null) SUCCESS else ERROR_CREATE_COMPANY

        return BaseResponse.SuccessResponse(
            data = company,
            message = response
        )
    }

    override suspend fun updateCompany(id: UUID, params: CompanyParams): BaseResponse<Any> {
        val companyId = companyService.updateCompany(id, params)
        return BaseResponse.SuccessResponse(
            data = companyId,
            message = SUCCESS
        )
    }

    override suspend fun deleteCompany(id: UUID): BaseResponse<Any> {
        val deletedId = companyService.deleteCompany(id)
        return BaseResponse.SuccessResponse(
            data = deletedId,
            message = SUCCESS
        )
    }

    override suspend fun getCompanyJobs(id: UUID): BaseResponse<Any> {
       val cpmpanyjob = companyService.getCompanyJobs(id)
        return BaseResponse.SuccessResponse(
            data = cpmpanyjob,
            message = SUCCESS
        )
    }
    // Implement các phương thức khác...
}