package com.concept.user.info.di

import com.concept.user.info.UserInfoFragment
import dagger.Subcomponent

@UserInfoScope
@Subcomponent(modules = arrayOf(UserInfoModule::class))
interface UserInfoComponent {
    fun inject(target: UserInfoFragment)
}