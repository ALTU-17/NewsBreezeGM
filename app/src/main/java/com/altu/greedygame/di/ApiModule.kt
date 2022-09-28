package com.altu.greedygame.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.altu.greedygame.data.remote.RemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    @Singleton
    internal fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            var request: Request? = null
            try {
                request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .build()

            } catch (authFailureError: Exception) {
                authFailureError.printStackTrace()
            }

            chain.proceed(request!!)
        }
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.interceptors().add(interceptor)
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://newsapi.org/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RemoteApiService = retrofit.create(RemoteApiService::class.java)


}