package com.concept.user.info.ui.models.presenter

import com.concept.user.info.ui.models.UserModel
import com.concept.user.info.ui.models.interactor.UserInfoInteractor
import com.concept.user.info.ui.models.view.UserInfoView
import com.concept.user.util.NetworkStateHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class UserInfoPresenterImpl(private var view: UserInfoView?,
                            private val userInfoInteractor: UserInfoInteractor,
                            private val networkStateHelper: NetworkStateHelper)
    : UserInfoPresenter {
    private val compositeDisposable = CompositeDisposable()
    override fun getUserInfo() {
        disposeRequests()
        view?.showLoadingUserInfo()
        userInfoInteractor.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userModel: UserModel? ->
                    view?.hideLoadingUserInfo()
                    if (userModel == null) {
                        view?.showFailedToLoadUserInfo()
                    } else {
                        displayUserModel(userModel)
                    }
                }, { _ ->
                    view?.showFailedToLoadUserInfo()
                    view?.hideLoadingUserInfo()
                })
    }

    private fun displayUserModel(userModel: UserModel) {
        view?.displayFirstName(userModel.firstName)
        view?.displayLastName(userModel.lastName)
        view?.displayEmail(userModel.email)
        view?.displayProfileImage(userModel.profilePictureUrl)
        view?.displayPhoneNumber(userModel.phoneNumber)
    }

    override fun onDestroy() {
        disposeRequests()
        view = null
    }

    private fun disposeRequests() {
        compositeDisposable.clear()
    }

}