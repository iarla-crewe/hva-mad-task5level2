package com.example.madlevel5task2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel5task2.model.Game

@Dao
interface GameDao {
    @Query ("SELECT * FROM gameTable")
    fun getGames(): LiveData<List<Game>>

    @Insert
    fun insertGame(game: Game)

    @Delete
    fun deleteGame(game: Game)

    @Query ("DELETE FROM gameTable")
    fun deleteAllGames()
}