package com.iamagamedev.trainingapp.ui.story

import android.os.Bundle
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import kotlinx.android.synthetic.main.toolbar.*

class StoryActivity : GeneralActivityWithAppBar() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        toolbar_text.text = this::class.java.simpleName
    }
}
