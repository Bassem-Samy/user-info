package com.concept.user.info.ui.models.presenter

import com.concept.user.info.ui.models.view.UserInfoView


interface UserInfoPresenter {
    fun getUserInfo()
    fun onDestroy()

}