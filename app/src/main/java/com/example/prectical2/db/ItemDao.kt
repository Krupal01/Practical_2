package com.example.prectical2.db

import androidx.room.*
import com.example.prectical2.model.ItemsItem

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(user : ItemsItem)

    @Query("select * from ItemsItem")
    suspend fun getSavedItems() : List<ItemsItem>

    @Delete
    suspend fun deleteItems(itemsItem: ItemsItem)

}