package com.concept.user.info.ui.models


data class UserModel(

        var id: String,
        var firstName: String?,
        var lastName: String?,
        var phoneNumber: String?,
        var email: String?,
        var profilePictureUrl: String?
)