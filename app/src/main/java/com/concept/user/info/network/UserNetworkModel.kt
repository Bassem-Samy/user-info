package com.concept.user.info.network

import com.google.gson.annotations.SerializedName


data class UserNetworkModel(
        @SerializedName("id")
        var id: String?,
        @SerializedName("firstName")
        var firstName: String?,
        @SerializedName("lastName")
        var lastName: String?,
        @SerializedName("phoneNumber")
        var phoneNumber: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("profilePicture")
        var profilePictureUrl: String?
)