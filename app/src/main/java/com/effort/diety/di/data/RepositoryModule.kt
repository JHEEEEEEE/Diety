package com.effort.diety.di.data

import com.effort.diety.data.repository.AuthRepository
import com.effort.diety.data.repository.DietRepository
import com.effort.diety.data.repository.ProfileRepository
import com.effort.diety.data.repositoryimpl.AuthRepositoryImpl
import com.effort.diety.data.repositoryimpl.DietRepositoryImpl
import com.effort.diety.data.repositoryimpl.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDietRepository(dietRepositoryImpl: DietRepositoryImpl): DietRepository
}