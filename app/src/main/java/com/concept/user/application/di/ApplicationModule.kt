package com.concept.user.application.di

import android.app.Application
import com.concept.user.util.GlideImageLoader
import com.concept.user.util.ImageLoader
import com.concept.user.util.NetworkStateHelper
import com.concept.user.util.NetworkStateHelperImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: Application, private val baseUrl: String) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    internal fun providesConverterFactory(): Converter.Factory {
        val gson = GsonBuilder().disableHtmlEscaping().create()
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    internal fun providesCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    internal fun providesRetrofit(converterFactory: Converter.Factory, callAdapterFactory: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .baseUrl(baseUrl)
                .build()
    }

    @Singleton
    @Provides
    internal fun providesNetworkStateHelper(application: Application): NetworkStateHelper {
        return NetworkStateHelperImpl(application)
    }

    @Singleton
    @Provides
    internal fun providesImageLoader(): ImageLoader {
        return GlideImageLoader()
    }

}