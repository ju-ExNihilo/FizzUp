package com.jgdeveloppement.fizzup.injection

import android.content.Context
import com.jgdeveloppement.fizzup.factory.ViewModelFactory
import com.jgdeveloppement.fizzup.repository.ExerciseRepository
import com.jgdeveloppement.fizzup.retrofit.ApiHelper
import com.jgdeveloppement.fizzup.retrofit.RetrofitService
import com.jgdeveloppement.fizzup.room.ExerciseDataBase

object Injection {

    private fun provideExerciseRepository(context: Context) : ExerciseRepository{
        val apiHelper = ApiHelper(RetrofitService.apiService)
        val db = ExerciseDataBase.getDatabase(context)
        val exerciseDao = db?.exerciseDao()
        return ExerciseRepository(apiHelper, exerciseDao)
    }

    fun provideRetrofitViewModelFactory(context: Context) : ViewModelFactory{
        val exerciseRepository = provideExerciseRepository(context)
        return ViewModelFactory(exerciseRepository)
    }
}