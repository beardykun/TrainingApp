package com.iamagamedev.trainingapp.ui.thisTraining

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.iamagamedev.trainingapp.R
import org.jetbrains.anko.find

class ThisTrainingAdapter : RecyclerView.Adapter<ThisTrainingAdapter.ThisTrainingViewHolder>() {

    private var list: MutableList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ThisTrainingViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.this_training_item, parent, false)

        return ThisTrainingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (list == null)
            0
        else
            list!!.size
    }

    override fun onBindViewHolder(holder: ThisTrainingViewHolder, position: Int) {
        if (list != null)
            holder.text.text = list!![position]
    }

    fun swapAdapter(list: MutableList<String>) {
        this.list = list
    }

    class ThisTrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.find<TextView>(R.id.thisTrainingExerciseTextView)
    }
}