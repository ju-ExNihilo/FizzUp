package com.jgdeveloppement.fizzup.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jgdeveloppement.fizzup.repository.ExerciseRepository
import com.jgdeveloppement.fizzup.retrofit.ApiHelper
import com.jgdeveloppement.fizzup.viewmodel.ExerciseViewModel

class ViewModelFactory(private val exerciseRepository: ExerciseRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            return ExerciseViewModel(exerciseRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}