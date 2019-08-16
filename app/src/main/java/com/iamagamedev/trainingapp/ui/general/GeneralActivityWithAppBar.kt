package com.iamagamedev.trainingapp.ui.general

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar

import androidx.core.app.NavUtils
import com.iamagamedev.trainingapp.R
import kotlinx.android.synthetic.main.activity_general_with_appbar.*

abstract class GeneralActivityWithAppBar : GeneralActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setContentView(layoutResID: Int) {
        val layoutInflater = layoutInflater

        val container = layoutInflater.inflate(R.layout.activity_general_with_appbar, window.decorView as ViewGroup, false)

        layoutInflater.inflate(layoutResID, container.findViewById(R.id.cont_root) as ViewGroup, true)
        super.setContentView(container)

        progressLay?.visibility = View.GONE

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val bar = supportActionBar
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false)
            bar.setDisplayHomeAsUpEnabled(true)
            bar.setDisplayShowHomeEnabled(true)
            bar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        }
    }
}
