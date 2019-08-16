package com.iamagamedev.trainingapp.ui.training

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.iamagamedev.trainingapp.R
import org.jetbrains.anko.find

class CreateTrainingFragment : DialogFragment(), TextView.OnEditorActionListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_training, container, false)
        view.find<EditText>(R.id.trainingFragmentText).setOnEditorActionListener(this)
        return view
    }


    override fun onEditorAction(textView: TextView?, i: Int, keyEvent: KeyEvent?): Boolean {
       if (i == EditorInfo.IME_ACTION_DONE){
           (activity as TrainingActivity).updateList(textView?.text.toString())
           dismiss()
           return true
       }
        return false
    }
}
