package com.concept.user

import com.concept.user.info.network.UserNetworkModel
import com.concept.user.info.repository.UserRepository
import com.concept.user.info.ui.models.UserModel
import com.concept.user.info.ui.models.interactor.UserInfoInteractor
import com.concept.user.info.ui.models.interactor.UserInfoInteractorImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserInfoInteractorUnitTest {
    private lateinit var interactor: UserInfoInteractor
    @Mock
    private lateinit var repository: UserRepository

    @Before
    fun setup() {
        interactor = UserInfoInteractorImpl(repository)
    }

    @Test
    fun testGetUserWithCorrectValues() {
        val testUserNetworkModel = UserNetworkModel(
                "0da17602-dad9-4a9e-9d4c-772252f65777",
                "John",
                "Alex",
                "123123123",
                "email@email.com",
                null
        )
        Mockito.`when`(repository.getUser()).thenReturn(Single.just(testUserNetworkModel))
        val testObserver = interactor.getUser().test()
        testObserver.assertValue { t: UserModel ->
            return@assertValue t.id == "0da17602-dad9-4a9e-9d4c-772252f65777"
                    && t.firstName == "John"
                    && t.lastName == "Alex"
                    && t.phoneNumber == "123123123"
                    && t.email == "email@email.com"
                    && t.profilePictureUrl == null
        }
    }

    @Test
    fun testGetUserWithNullValues() {
        val testUserNetworkModel = UserNetworkModel(
                null,
                null,
                null,
                null,
                null,
                null
        )
        Mockito.`when`(repository.getUser()).thenReturn(Single.just(testUserNetworkModel))
        val testObserver = interactor.getUser().test()
        testObserver.assertValue { t: UserModel ->
            return@assertValue t.id == "1"
                    && t.firstName == null
                    && t.lastName == null
                    && t.phoneNumber == null
                    && t.email == null
                    && t.profilePictureUrl == null
        }
    }
}