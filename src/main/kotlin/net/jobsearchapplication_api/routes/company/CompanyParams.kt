package net.jobsearchapplication_api.routes.company

import java.util.UUID

data class CompanyParams(
    val name: String,
    val description: String? = null,
    val location: String? = null
)