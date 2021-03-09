package com.jgdeveloppement.fizzup.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jgdeveloppement.fizzup.repository.ExerciseRepository

class ExerciseViewModel(private val exerciseRepository: ExerciseRepository) : ViewModel() {

     fun getExerciseList(isConnected: Boolean) = exerciseRepository.getExerciseList(isConnected)

     fun parseJsonFromAsset(context: Context) = exerciseRepository.parseJsonFromAsset(context)

}