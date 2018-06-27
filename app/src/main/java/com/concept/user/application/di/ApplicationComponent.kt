package com.concept.user.application.di

import com.concept.user.info.di.UserInfoComponent
import com.concept.user.info.di.UserInfoModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun plus(module: UserInfoModule): UserInfoComponent
}