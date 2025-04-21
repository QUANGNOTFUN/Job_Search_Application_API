package net.jobsearchapplication_api.routes.user

import com.google.gson.annotations.SerializedName
import java.util.*

data class FavoriteParams(
    @SerializedName("jobId") val jobId: UUID,
    @SerializedName("status") val status: Boolean
)