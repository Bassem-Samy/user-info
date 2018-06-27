package com.concept.user.info.ui.models.view


interface UserInfoView {
    fun showLoadingUserInfo()
    fun showFailedToLoadUserInfo()
    fun hideLoadingUserInfo()
    fun displayFirstName(firstName: String?)
    fun displayLastName(lastName: String?)
    fun displayEmail(email: String?)
    fun displayProfileImage(url: String?)
    fun displayPhoneNumber(phoneNumber: String?)
}