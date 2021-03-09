package com.jgdeveloppement.fizzup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jgdeveloppement.fizzup.models.Exercise
import com.jgdeveloppement.fizzup.room.ExerciseDao
import com.jgdeveloppement.fizzup.room.ExerciseDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ExerciseDaoTest {

    private val exercise1 = Exercise(1, "testName", "TestUrl")
    private val exercise2 = Exercise(2, "testName", "TestUrl")
    private val exerciseList = listOf(exercise1, exercise2)
    private lateinit var database: ExerciseDataBase
    private lateinit var exerciseDao: ExerciseDao
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ExerciseDataBase::class.java).build()
        exerciseDao = database.exerciseDao()
    }

    @After
    fun cleanup() {
        database.close()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun insertListExerciseGetAndDelete(): Unit = runBlocking {

        val numberExerciseStart = exerciseDao.getExercises().size

        exerciseDao.insertAllExercise(exerciseList)

        var numberExerciseCurrent = exerciseDao.getExercises().size

        assertEquals(numberExerciseCurrent, numberExerciseStart + 2)
        assertEquals(exerciseDao.getExercise(1), exercise1)
        assertEquals(exerciseDao.getExercise(2), exercise2)

        exerciseDao.deleteExercise(1)
        exerciseDao.deleteExercise(2)

        numberExerciseCurrent = exerciseDao.getExercises().size

        assertEquals(numberExerciseStart, numberExerciseCurrent )
    }
}
