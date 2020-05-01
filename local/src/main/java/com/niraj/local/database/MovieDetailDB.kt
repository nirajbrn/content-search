package com.niraj.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.niraj.local.model.MovieLocal

@Database(entities = [MovieLocal::class], version = 1, exportSchema = false)
abstract class MovieDetailDB : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "bank_buddy.db"
        @Volatile
        private var INSTANCE: MovieDetailDB? = null

        fun getInstance(@NonNull context: Context): MovieDetailDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            MovieDetailDB::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getMovieDao(): MovieDAO
}