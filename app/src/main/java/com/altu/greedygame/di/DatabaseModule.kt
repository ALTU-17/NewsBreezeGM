package com.altu.greedygame.di

import android.app.Application
import com.altu.greedygame.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides
    @Singleton
    fun provideDatabase(application: Application)= NewsDatabase.getInstance(application)


    @Singleton
    @Provides
    fun providesNewsDao(database: NewsDatabase)=database.newsDao()

}