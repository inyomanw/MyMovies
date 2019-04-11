package com.inyomanw.mymovies.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.inyomanw.mymovies.data.local.dao.PopularMovieDao
import com.inyomanw.mymovies.data.local.entity.PopularMovieEntity

@Database(entities = arrayOf(
    PopularMovieEntity::class
),version = 1)
abstract class  AppDatabase : RoomDatabase(){

    abstract fun popularMovieDao() : PopularMovieDao


    companion object {
        private const val DB_NAME ="db_movies"
        private var instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance==null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}