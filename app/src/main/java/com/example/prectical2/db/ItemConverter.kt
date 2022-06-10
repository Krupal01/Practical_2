package com.example.prectical2.db

import androidx.room.TypeConverter
import com.example.prectical2.model.BadgeCounts
import com.google.gson.Gson

class ItemConverter {

    @TypeConverter
    fun fromBadgeCounts(badgeCounts: BadgeCounts?): String? {
        return Gson().toJson(badgeCounts)
    }

    @TypeConverter
    fun toBadgeCount(badgeString: String?):BadgeCounts?{
        return Gson().fromJson(badgeString,BadgeCounts::class.java)
    }

}