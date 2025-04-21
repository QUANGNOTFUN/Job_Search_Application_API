package net.jobsearchapplication_api.routes.user

import com.google.gson.annotations.SerializedName

data class UpdateInfoUserParams(
    @SerializedName("avatar") val avatar: String = "",

    @SerializedName("bio") val bio: String = "",

    @SerializedName("fullName") val fullName: String = "",

    @SerializedName("gender") val gender: String = "",

    @SerializedName("birthDay") val birthDay: String = "",

    @SerializedName("phoneNumber") val phoneNumber: String = "",

    @SerializedName("cvUrl") val cvUrl: String = "",

    @SerializedName("role") val role: String? = "",
)