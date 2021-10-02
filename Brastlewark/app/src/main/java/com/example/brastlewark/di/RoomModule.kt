package com.example.brastlewark.di

import android.content.Context
import androidx.room.Room
import com.example.brastlewark.data.room.BrastlewarkDao
import com.example.brastlewark.data.room.BrastlewarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        BrastlewarkDatabase::class.java,
        "Brastlewark_Db"
    ).build()

    @Singleton
    @Provides
    fun provideBrastlewarkDao(db: BrastlewarkDatabase):BrastlewarkDao = db.getBrastlewarkDao()
}