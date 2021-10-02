package com.example.brastlewark.di

import com.example.brastlewark.data.network.BrastlewarkApiCliente
import com.example.brastlewark.data.room.BrastlewarkDao
import com.example.brastlewark.data.room.BrastlewarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/rrafols/mobile_test/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit):BrastlewarkApiCliente{
        return retrofit.create(BrastlewarkApiCliente::class.java)
    }


}