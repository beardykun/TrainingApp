package com.iamagamedev.trainingapp.ui.createNewExercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.ThisApplication
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource

class CreateNewExerciseAdapter : RecyclerView.Adapter<CreateNewExerciseAdapter.CreateNewExerciseViewHolder>() {

    val array = ThisApplication.instance.resources.getStringArray(R.array.muscle_groups)
    val imageArray = arrayOf(R.drawable.ic_fitness_center_black_24dp, R.drawable.ic_fitness_center_black_24dp, R.drawable.ic_fitness_center_black_24dp,
            R.drawable.ic_fitness_center_black_24dp, R.drawable.ic_fitness_center_black_24dp, R.drawable.ic_fitness_center_black_24dp, R.drawable.ic_fitness_center_black_24dp)

    interface OnCreateNewExerciseListener{
        fun chooseMuscleGroup(imageId: Int, muscleGroup: String)
    }

    private var listener:OnCreateNewExerciseListener? = null

    fun setOnCreateNewExerciseListener(listener: OnCreateNewExerciseListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CreateNewExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.this_training_item, parent, false)
        return CreateNewExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: CreateNewExerciseViewHolder, position: Int) {
        if (listener != null){
            holder.itemView.setOnClickListener { listener?.chooseMuscleGroup(imageArray[holder.adapterPosition], array[holder.adapterPosition]) }
        }
        holder.icon.imageResource = imageArray[position]
        holder.text.text = array[position]
    }

    class CreateNewExerciseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val icon = itemView.find<ImageView>(R.id.exerciseChoiceImage)
        val text = itemView.find<TextView>(R.id.thisTrainingExerciseTextView)
    }
}