package com.concept.user

import com.bumptech.glide.load.engine.cache.DiskCache
import com.concept.user.info.ui.models.UserModel
import com.concept.user.info.ui.models.interactor.UserInfoInteractor
import com.concept.user.info.ui.models.presenter.UserInfoPresenter
import com.concept.user.info.ui.models.presenter.UserInfoPresenterImpl
import com.concept.user.info.ui.models.view.UserInfoView
import com.concept.user.util.NetworkStateHelper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.reset
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

@RunWith(MockitoJUnitRunner::class)


class UserPresenterUnitTest {
    private lateinit var presenter: UserInfoPresenter
    @Mock
    private lateinit var view: UserInfoView
    @Mock
    private lateinit var interactor: UserInfoInteractor
    @Mock
    private lateinit var networkStateHelper: NetworkStateHelper

    @Before
    fun setup() {

        presenter = UserInfoPresenterImpl(view, interactor, networkStateHelper)
    }

    @Test
    fun testPresenterGetUser() {
        reset(networkStateHelper)
        reset(interactor)
        reset(view)
        Mockito.`when`(networkStateHelper.hasInternet()).thenReturn(true)
        val userModel = UserModel(
                "0da17602-dad9-4a9e-9d4c-772252f65777",
                "John",
                "Alex",
                "123123123",
                "email@email.com",
                null
        )
        Mockito.`when`(interactor.getUser()).thenReturn(Single.just(userModel))
        presenter.getUserInfo(Schedulers.io())
        verify(interactor).getUser()
        verify(view).displayUserName("John", "Alex")
        verify(view).displayEmail("email@email.com")
        verify(view).displayPhoneNumber("123123123")
        verify(view).displayProfileImage(null)

    }

    @Test
    fun testPresenterDeleteUser() {
        reset(networkStateHelper)
        reset(interactor)
        reset(view)
        val emptyResponseBody: ResponseBody = ResponseBody.create(MediaType.parse("*"), "empty")
        Mockito.`when`(networkStateHelper.hasInternet()).thenReturn(true)
        Mockito.`when`(interactor.deleteUser()).thenReturn(Single.just(Result.response(Response.success(
                emptyResponseBody
        ))))

        presenter.deleteUser(Schedulers.io())

        verify(interactor).deleteUser()
        verify(view).showLoadingDeletingUser()
        verify(view).hideUserInfo()
        verify(view).hideLoadingDeletingUser()
        verify(view).showSuccessfullyDeletedUser()
    }

    @Test
    fun testNoInternetGetUserInfo() {
        reset(networkStateHelper)
        reset(interactor)
        reset(view)

        Mockito.`when`(networkStateHelper.hasInternet()).thenReturn(false)

        presenter.getUserInfo(Schedulers.io())
        verify(view).showNoInternet()

    }

    @Test
    fun testNoInternetDeleteUser() {
        reset(networkStateHelper)
        reset(interactor)
        reset(view)

        Mockito.`when`(networkStateHelper.hasInternet()).thenReturn(false)

        presenter.deleteUser(Schedulers.io())
        verify(view).showNoInternet()

    }


}