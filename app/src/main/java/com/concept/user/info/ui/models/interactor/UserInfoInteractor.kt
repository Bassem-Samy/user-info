package com.concept.user.info.ui.models.interactor

import com.concept.user.info.ui.models.UserModel
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result


interface UserInfoInteractor {


    fun getUser(): Single<UserModel?>
    fun deleteUser(id: String): Single<Result<Void>>
}