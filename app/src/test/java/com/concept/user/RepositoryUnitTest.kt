package com.concept.user

import com.concept.user.info.network.UserNetworkModel
import com.concept.user.info.network.UserService
import com.concept.user.info.repository.UserRepository
import com.concept.user.info.repository.UserRepositoryImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

@RunWith(MockitoJUnitRunner::class)

class RepositoryUnitTest {
    private lateinit var userRepository: UserRepository
    @Mock
    private lateinit var userService: UserService

    @Before
    fun setup() {

        userRepository = UserRepositoryImpl(userService)
    }

    @Test
    fun testRepositoryGetUser() {
        val testUser = UserNetworkModel(
                "123",
                "first name",
                "last name",
                null,
                "email@email.com",
                "null"
        )
        // test the user network model is returned
        Mockito.`when`(userService.getUser()).thenReturn(Single.just(testUser))
        userRepository.getUser().test().assertResult(testUser)

    }


}