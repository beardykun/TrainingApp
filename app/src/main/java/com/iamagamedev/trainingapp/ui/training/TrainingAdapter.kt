package com.iamagamedev.trainingapp.ui.training

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import org.jetbrains.anko.find

class TrainingAdapter : RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    private var listener: OnTrainingItemListener? = null
    private var listenerLong: OnTrainingItemLongListener? = null
    private var trainingList: List<TrainingObject>? = null

    interface OnTrainingItemListener {
        fun onTrainingListItemClick(name: String)
    }

    interface OnTrainingItemLongListener {
        fun onTrainingListItemLongClick(name: String)
    }

    fun setOnTrainingItemListener(listener: OnTrainingItemListener) {
        this.listener = listener
    }

    fun setOnTrainingItemLongListener(listener: OnTrainingItemLongListener) {
        this.listenerLong = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.training_list_item, parent, false)

        return TrainingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (trainingList == null){
            0
        }else
            trainingList!!.size
    }

    fun swapAdapter(list: List<TrainingObject>){
        trainingList = list
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        if (trainingList != null) {
            if (listener != null) {
                holder.itemView.setOnClickListener {
                    listener?.onTrainingListItemClick(trainingList!![holder.adapterPosition].trainingName)
                }
                holder.itemView.setOnLongClickListener {
                    listenerLong?.onTrainingListItemLongClick(trainingList!![holder.adapterPosition].trainingName)
                    true
                }
            }

            holder.text.text = trainingList!![position].trainingName
        }else{
            holder.text.text = "Empty List"
        }
    }

    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.find<TextView>(R.id.trainingListText)
    }
}