package com.iamagamedev.trainingapp.ui.achievements

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iamagamedev.trainingapp.R
import kotlinx.android.synthetic.main.toolbar.*

class AchievementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        toolbar_text.text = this::class.java.simpleName
    }
}
