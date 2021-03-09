package com.jgdeveloppement.fizzup.retrofit

import com.jgdeveloppement.fizzup.models.Exercise
import retrofit2.http.GET
import retrofit2.http.Multipart

interface ApiService {

    @GET("/sample.json")
    suspend fun getExercise(): List<Exercise>
}