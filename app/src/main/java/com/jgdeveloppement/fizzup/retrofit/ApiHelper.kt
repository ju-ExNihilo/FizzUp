package com.jgdeveloppement.fizzup.retrofit

class ApiHelper(private val apiService: ApiService) {

    suspend fun getExercise() = apiService.getExercise()
}