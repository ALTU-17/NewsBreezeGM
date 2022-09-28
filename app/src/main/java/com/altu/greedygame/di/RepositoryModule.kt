package com.altu.greedygame.di

import com.altu.greedygame.data.repository.Repository
import com.altu.greedygame.data.repository.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(repository: RepositoryImp): Repository
}