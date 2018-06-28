package com.concept.user.info.ui.models.interactor

import com.concept.user.info.repository.UserRepository
import com.concept.user.info.ui.models.UserModel
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result


class UserInfoInteractorImpl(private val userRepository: UserRepository) : UserInfoInteractor {
    private var userId = "1"
    override fun getUser(): Single<UserModel?> {
        return userRepository.getUser().map { model ->
            if (model != null) {
                model?.let {
                    it.id?.let { id -> userId = id }
                    return@map UserModel(
                            it.id ?: userId,
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

    override fun deleteUser(): Single<Result<Void>> {
        return userRepository.deleteUser(userId)
    }
}