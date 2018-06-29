package com.concept.user.info.ui.models.view


interface UserInfoView {
    fun showLoadingUserInfo()
    fun showFailedToLoadUserInfo()
    fun hideLoadingUserInfo()
    fun displayUserName(firstName: String?, lastName: String?)
    fun displayEmail(email: String?)
    fun displayProfileImage(url: String?)
    fun displayPhoneNumber(phoneNumber: String?)
    fun showUserInfoLayout()
    fun showLoadingDeletingUser()
    fun showSuccessfullyDeletedUser()
    fun showFailedToDeleteUser()
    fun hideLoadingDeletingUser()
    fun showLoadAgainUserInfo()
    fun hideLoadAgainuserInfo()
    fun hideUserInfo()
    fun showNoInternet()
}