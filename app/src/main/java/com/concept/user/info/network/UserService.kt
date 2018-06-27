package com.concept.user.info.network

import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("user/all")
    fun getUser(): Single<UserNetworkModel>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: String): Single<Result<Void>>
}