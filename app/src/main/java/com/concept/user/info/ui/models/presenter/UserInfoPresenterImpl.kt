package com.concept.user.info.ui.models.presenter

import com.concept.user.info.ui.models.UserModel
import com.concept.user.info.ui.models.interactor.UserInfoInteractor
import com.concept.user.info.ui.models.view.UserInfoView
import com.concept.user.util.NetworkStateHelper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class UserInfoPresenterImpl(private var view: UserInfoView?,
                            private val userInfoInteractor: UserInfoInteractor,
                            private val networkStateHelper: NetworkStateHelper)
    : UserInfoPresenter {
    private val compositeDisposable = CompositeDisposable()
    override fun getUserInfo(observeOnScheduler: Scheduler) {
        disposeRequests()
        if (!networkStateHelper.hasInternet()) {
            view?.showNoInternet()
            return
        }
        view?.showLoadingUserInfo()
        view?.hideLoadAgainuserInfo()
        userInfoInteractor.getUser().subscribeOn(Schedulers.io())
                .observeOn(observeOnScheduler)
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

    override fun deleteUser(observeOnScheduler: Scheduler) {
        if (!networkStateHelper.hasInternet()) {
            view?.showNoInternet()
            return
        }
        view?.showLoadingDeletingUser()
        view?.hideUserInfo()
        disposeRequests()
        userInfoInteractor.deleteUser().subscribeOn(Schedulers.io())
                .observeOn(observeOnScheduler)
                .subscribe({ result ->
                    view?.hideLoadingDeletingUser()
                    if (result.response().code() == 200) {

                        view?.showSuccessfullyDeletedUser()
                    } else {
                        view?.showFailedToDeleteUser()
                    }
                    view?.showLoadAgainUserInfo()
                }, { _ ->
                    view?.hideLoadingDeletingUser()
                    view?.showFailedToDeleteUser()
                    view?.showLoadAgainUserInfo()
                })
    }

    private fun displayUserModel(userModel: UserModel) {
        view?.showUserInfoLayout()
        view?.displayUserName(userModel.firstName, userModel.lastName)
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