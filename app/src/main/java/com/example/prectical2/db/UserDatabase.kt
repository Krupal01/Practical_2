package com.example.prectical2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.prectical2.model.ItemsItem

@Database(entities = [ItemsItem::class],version = 1)
@TypeConverters(ItemConverter::class)
abstract class UserDatabase : RoomDatabase(){

    abstract fun getItemDao() : ItemDao

}