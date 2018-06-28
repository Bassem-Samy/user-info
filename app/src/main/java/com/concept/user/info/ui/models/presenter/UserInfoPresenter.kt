package com.concept.user.info.ui.models.presenter

import com.concept.user.info.ui.models.view.UserInfoView
import io.reactivex.Scheduler


interface UserInfoPresenter {
    fun getUserInfo(observeOnScheduler: Scheduler)
    fun onDestroy()
    fun deleteUser(observeOnScheduler: Scheduler)

}