package com.concept.user.info.repository

import com.concept.user.info.network.UserNetworkModel
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result


interface UserRepository {
    fun getUser(): Single<UserNetworkModel>
    fun deleteUser(id: String): Single<Result<String?>>
}