package net.jobSearchApplication_api.routes.favorite

import com.google.gson.annotations.SerializedName
import java.util.*

data class FavoriteParams(
    @SerializedName("jobId") val jobId: UUID,
    @SerializedName("status") val status: Boolean
)