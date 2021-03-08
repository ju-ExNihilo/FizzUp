package com.jgdeveloppement.fizzup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jgdeveloppement.fizzup.databinding.ActivityHomeBinding
import com.jgdeveloppement.fizzup.home.ExerciseAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
    }

    private fun setAdapter(){
        binding.exerciseRecyclerView.adapter = ExerciseAdapter(this)
    }
}
