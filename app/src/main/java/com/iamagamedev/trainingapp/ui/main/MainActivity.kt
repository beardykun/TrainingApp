package com.iamagamedev.trainingapp.ui.main

import android.os.Bundle
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import com.iamagamedev.trainingapp.ui.training.TrainingActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.main_activity_item.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : GeneralActivityWithAppBar() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_item)

        toolbar_text.text = this::class.java.simpleName
        val series: LineGraphSeries<DataPoint> = LineGraphSeries(arrayOf(DataPoint(0.toDouble(), 1.toDouble()), DataPoint(1.toDouble(), 2.toDouble()),
                DataPoint(2.toDouble(), 0.toDouble())))
        graphView.addSeries(series)
    }

    override fun onStart() {
        super.onStart()
        training_container.setOnClickListener { startActivity(TrainingActivity::class.java) }
    }
}
