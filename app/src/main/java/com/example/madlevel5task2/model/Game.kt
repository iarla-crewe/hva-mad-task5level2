package com.example.madlevel5task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "gameTable")
data class Game (
    @ColumnInfo
    val Title: String,

    @ColumnInfo
    val Platform: String,

    @ColumnInfo
    val ReleaseDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Long? = null
)