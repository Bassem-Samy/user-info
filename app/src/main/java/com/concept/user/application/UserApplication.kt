package com.concept.user.application

import android.app.Application
import com.concept.user.application.di.ApplicationComponent
import com.concept.user.application.di.ApplicationModule
import com.concept.user.application.di.DaggerApplicationComponent
import com.concept.user.info.di.UserInfoComponent
import com.concept.user.info.di.UserInfoModule
import com.concept.user.util.Constants


class UserApplication : Application() {
    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this, Constants.BASE_URL))
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return this.applicationComponent
    }

    companion object {
        fun get(application: Application): UserApplication {
            return application as UserApplication
        }
    }

}