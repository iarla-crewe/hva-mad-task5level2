package com.example.madlevel5task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameTable")
data class Game (
    @ColumnInfo
    val title: String,

    @ColumnInfo
    val platform: String,

    @ColumnInfo
    val releaseDate: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Long? = null
)