package com.jgdeveloppement.fizzup.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jgdeveloppement.fizzup.models.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllExercise(exercise: List<Exercise>)

    @Query("SELECT * from exercise ORDER BY id ASC")
    suspend fun getExercises() : List<Exercise>

    @Query("DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExercise(id: Int)

    @Query("SELECT * from exercise WHERE id = :id")
    suspend fun getExercise(id: Int) : Exercise

}