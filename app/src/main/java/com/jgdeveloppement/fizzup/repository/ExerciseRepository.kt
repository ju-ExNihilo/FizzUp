package com.jgdeveloppement.fizzup.repository

import android.content.Context
import androidx.lifecycle.liveData
import com.jgdeveloppement.fizzup.models.Exercise
import com.jgdeveloppement.fizzup.retrofit.ApiHelper
import com.jgdeveloppement.fizzup.retrofit.Resource
import com.jgdeveloppement.fizzup.room.ExerciseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class ExerciseRepository(private val apiHelper: ApiHelper, private val exerciseDao: ExerciseDao?) {

    // Looking connexion and return data from Api or SQLite
    fun getExerciseList(isConnected: Boolean) = if(isConnected) getExerciseFromRetrofit() else getExerciseFromRoom()

    // Get Data from retrofit
    private fun getExerciseFromRetrofit() = liveData(IO) {
        emit(Resource.loading(data = null))
        try {
            insertAllExercise(apiHelper.getExercise())
            emit(Resource.success(data = apiHelper.getExercise()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    // Get Data from room
    private fun getExerciseFromRoom() = liveData(IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = exerciseDao?.getExercises()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    // Insert Data to SQLite
    private fun insertAllExercise(exerciseList: List<Exercise>) {
        CoroutineScope(IO).launch { exerciseDao?.insertAllExercise(exerciseList) }
    }

    // For init data with assets/sample.json with true Api use Retrofit
    fun parseJsonFromAsset(context: Context) : List<Exercise>{
        val exerciseList: ArrayList<Exercise> = ArrayList()
        try {
            val obj = JSONObject(loadJSONFromAsset(context))
            val dataArray = obj.getJSONArray("data")
            for (i in 0 until dataArray.length()){
                val exerciseDetail = dataArray.getJSONObject(i)
                val exercise = Exercise(exerciseDetail.getInt("id"), exerciseDetail.getString("name"), exerciseDetail.getString("image_url"))
                exerciseList.add(exercise)
            }
        }catch (e: JSONException) {
            e.printStackTrace()
        }

        insertAllExercise(exerciseList)
        return exerciseList
    }

    private fun loadJSONFromAsset(context: Context): String {
        val json: String?
        try {
            val inputStream = context.assets.open("sample.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

}