package com.jgdeveloppement.fizzup.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jgdeveloppement.fizzup.models.Exercise

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class ExerciseDataBase : RoomDatabase(){

    abstract fun exerciseDao(): ExerciseDao

    companion object {

        @Volatile
        private var INSTANCE: ExerciseDataBase? = null

        fun getDatabase(context: Context): ExerciseDataBase? {
            if (INSTANCE == null) {
                synchronized(ExerciseDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, ExerciseDataBase::class.java, "exercise.db")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}