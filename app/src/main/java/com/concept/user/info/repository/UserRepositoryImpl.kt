package com.concept.user.info.repository

import com.concept.user.info.network.UserNetworkModel
import com.concept.user.info.network.UserService
import io.reactivex.Single
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result


class UserRepositoryImpl(private val service: UserService) : UserRepository {
    override fun getUser(): Single<UserNetworkModel> {
        return service.getUser()
    }

    override fun deleteUser(id: String): Single<Result<Void>> {
        return service.deleteUser(id)
    }
}