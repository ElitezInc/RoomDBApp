package com.gabrielius.roomdbapp.di

import com.gabrielius.roomdbapp.dao.CustomDAO
import com.gabrielius.roomdbapp.repository.DBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule
{
    @Provides
    @Singleton
    public fun provideCustomRepository(customDao : CustomDAO) : DBRepository
    {
        return DBRepository(customDao)
    }
}