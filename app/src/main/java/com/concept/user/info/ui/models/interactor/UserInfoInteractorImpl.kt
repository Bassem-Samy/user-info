package com.concept.user.info.ui.models.interactor

import com.concept.user.info.repository.UserRepository
import com.concept.user.info.ui.models.UserModel
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result


class UserInfoInteractorImpl(private val userRepository: UserRepository) : UserInfoInteractor {
    private val defaultId = "1"
    override fun getUser(): Single<UserModel?> {
        return userRepository.getUser().map { model ->
            if (model != null) {
                model?.let {
                    return@map UserModel(
                            it.id ?: defaultId,
                            it.firstName,
                            it.lastName,
                            it.phoneNumber,
                            it.email,
                            it.profilePictureUrl
                    )
                }
            } else {
                return@map null
            }
        }
    }

    override fun deleteUser(id: String): Single<Result<Void>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}