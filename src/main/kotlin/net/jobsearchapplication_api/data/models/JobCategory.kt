package net.jobsearchapplication_api.data.models

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class JobCategory(
    val id: Int,
    val name: String
)
