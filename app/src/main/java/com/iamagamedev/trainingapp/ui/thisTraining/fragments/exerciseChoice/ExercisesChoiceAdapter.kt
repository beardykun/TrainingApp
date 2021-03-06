package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource

class ExercisesChoiceAdapter :
        RecyclerView.Adapter<ExercisesChoiceAdapter.ExercisesChoiceViewHolder>() {

    private var list: List<ExerciseObject>? = null
    private var listener: OnExerciseChoiceItemListener? = null

    interface OnExerciseChoiceItemListener {
        fun onExerciseChoiceItemClick(trainingName: String, view: View)
    }

    fun setOnExerciseChoiceItemListener(listener: OnExerciseChoiceItemListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ExercisesChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_choice_item, parent, false)

        return ExercisesChoiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun swapAdapter(list: List<ExerciseObject>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ExercisesChoiceViewHolder, position: Int) {
        if (list!!.isNotEmpty()) {
            if (listener != null) {
                holder.itemView.setOnClickListener {

                    listener?.onExerciseChoiceItemClick(list!![position].exerciseName, holder.itemView)
                }
            }

            holder.text.text = list!![position].exerciseName
            holder.image.imageResource = list!![position].exerciseImage.toInt()
        } else {
            holder.text.text = "No exercises"
        }
    }

    class ExercisesChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.find<ImageView>(R.id.exerciseChoiceImage)
        val text = itemView.find<TextView>(R.id.exerciseChoiceText)
    }
}