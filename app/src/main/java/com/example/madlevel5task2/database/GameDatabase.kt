package com.example.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.madlevel5task2.model.Converters
import com.example.madlevel5task2.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun noteDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameDatabaseInstance: GameDatabase? = null

        fun getDatabase(context: Context) : GameDatabase? {
            if (gameDatabaseInstance == null) {
                synchronized(GameDatabase::class.java) {
                    if (gameDatabaseInstance == null) {
                        gameDatabaseInstance =
                            Room.databaseBuilder(
                                context.applicationContext,
                                GameDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return gameDatabaseInstance
        }
    }
}