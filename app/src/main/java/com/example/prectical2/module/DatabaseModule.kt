package com.example.prectical2.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prectical2.db.ItemDao
import com.example.prectical2.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context):UserDatabase{
        return Room.databaseBuilder(context,UserDatabase::class.java,"Users").build()
    }

    @Provides
    @Singleton
    fun getItemDao(userDatabase: UserDatabase):ItemDao{
        return userDatabase.getItemDao()
    }

}