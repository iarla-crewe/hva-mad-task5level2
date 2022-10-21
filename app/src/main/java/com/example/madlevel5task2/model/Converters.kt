package com.example.madlevel5task2.model

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun timestampToDate(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}