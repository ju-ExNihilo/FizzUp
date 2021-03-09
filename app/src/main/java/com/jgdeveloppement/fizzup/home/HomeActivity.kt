package com.jgdeveloppement.fizzup.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.jgdeveloppement.fizzup.databinding.ActivityHomeBinding
import com.jgdeveloppement.fizzup.injection.Injection
import com.jgdeveloppement.fizzup.models.Exercise
import com.jgdeveloppement.fizzup.networking.ConnexionInternet
import com.jgdeveloppement.fizzup.utils.Status
import com.jgdeveloppement.fizzup.viewmodel.ExerciseViewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var exerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        initExerciseList()
    }

    private fun setupViewModel() {
        exerciseViewModel = ViewModelProviders.of(this, Injection.provideRetrofitViewModelFactory(this)).get(ExerciseViewModel::class.java)
    }


    /////////////
    // Get Data
    private fun initExerciseList(){
        ConnexionInternet.isConnected { isConnected ->
            exerciseViewModel.getExerciseList(isConnected).observe(this, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            initViewLoading()
                            resource.data?.let { exercises -> setAdapter(exercises) }
                        }
                        Status.ERROR -> {
                            initViewLoading()
                            // For init data with assets/sample.json with true Api use Retrofit
                            setAdapter(exerciseViewModel.parseJsonFromAsset(applicationContext))
                            Log.i("DEBUGGG", "$isConnected from error")
                        }
                        Status.LOADING -> {
                            binding.homeProgressLayout.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }
    //////////////

    private fun initViewLoading(){
        binding.homeProgressLayout.visibility = View.GONE
        binding.exerciseRecyclerView.visibility = View.VISIBLE
    }

    private fun setAdapter(exercises: List<Exercise>) {
        binding.exerciseRecyclerView.adapter = ExerciseAdapter(this, exercises)
    }
}
