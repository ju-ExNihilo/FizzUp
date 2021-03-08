package com.jgdeveloppement.fizzup.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jgdeveloppement.fizzup.HomeActivity
import com.jgdeveloppement.fizzup.R

class ExerciseAdapter(private val context: HomeActivity) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 15

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }
}