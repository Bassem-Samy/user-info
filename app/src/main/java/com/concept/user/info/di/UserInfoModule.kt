package com.concept.user.info.di

import com.concept.user.info.network.UserService
import com.concept.user.info.repository.UserRepository
import com.concept.user.info.repository.UserRepositoryImpl
import com.concept.user.info.ui.models.interactor.UserInfoInteractor
import com.concept.user.info.ui.models.interactor.UserInfoInteractorImpl
import com.concept.user.info.ui.models.presenter.UserInfoPresenter
import com.concept.user.info.ui.models.presenter.UserInfoPresenterImpl
import com.concept.user.info.ui.models.view.UserInfoView
import com.concept.user.util.NetworkStateHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserInfoModule(private val view: UserInfoView) {
    @UserInfoScope
    @Provides
    internal fun providesUserInfoView() = view

    @UserInfoScope
    @Provides
    internal fun providesUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @UserInfoScope
    @Provides
    internal fun providesUserInfoRepository(service: UserService): UserRepository {
        return UserRepositoryImpl(service)
    }

    @UserInfoScope
    @Provides
    internal fun providesUserInfoInteractor(repository: UserRepository): UserInfoInteractor {
        return UserInfoInteractorImpl(repository)
    }

    @UserInfoScope
    @Provides
    internal fun providesUserInfoPresenter(view: UserInfoView, interactor: UserInfoInteractor, networkStateHelper: NetworkStateHelper): UserInfoPresenter {
        return UserInfoPresenterImpl(view, interactor, networkStateHelper)
    }
}