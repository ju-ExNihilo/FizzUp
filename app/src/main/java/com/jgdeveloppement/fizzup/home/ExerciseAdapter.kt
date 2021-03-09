package com.jgdeveloppement.fizzup.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jgdeveloppement.fizzup.R
import com.jgdeveloppement.fizzup.models.Exercise

class ExerciseAdapter(private val context: HomeActivity, private val exerciseList: List<Exercise>) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentExercise = exerciseList[position]

        Glide.with(context).load(Uri.parse(currentExercise.imageUrl)).into(holder.exerciseImageItem)
        holder.exerciseNameItem.text = currentExercise.name

    }

    override fun getItemCount(): Int = exerciseList.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val exerciseNameItem : TextView = view.findViewById(R.id.exercise_item_name)
        val exerciseImageItem : ImageView = view.findViewById(R.id.exercise_item_pic)
    }
}