package com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.utils.Utils
import kotlinx.android.synthetic.main.fragment_this_training.*

class ThisTrainingFragment : Fragment() {

    var trainingViewModel: TrainingViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_this_training, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        trainingViewModel?.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
                ?.observe(viewLifecycleOwner, Observer<TrainingObject> { training ->
                    val list = Utils.stringToList(training.trainingExerciseNameList)

                    val adapter = ThisTrainingAdapter()
                    adapter.swapAdapter(list)
                    recyclerViewThisTraining.adapter = adapter
                    MySharedPreferences.saveString(
                            Utils.getCurrentTrainingList(),
                            Utils.listToString(list))
                })
    }

    override fun onStop() {
        trainingViewModel = null
        super.onStop()
    }
}
