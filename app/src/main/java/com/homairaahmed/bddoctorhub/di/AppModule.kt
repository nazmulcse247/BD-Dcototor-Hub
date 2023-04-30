package com.homairaahmed.bddoctorhub.di

import android.content.Context
import com.homairaahmed.bddoctorhub.repository.AuthRepository
import com.homairaahmed.bddoctorhub.repository.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    fun provideAuthRepository() = AuthRepository()

    @Provides
    fun provideDashboardRepository() = DashboardRepository()
}