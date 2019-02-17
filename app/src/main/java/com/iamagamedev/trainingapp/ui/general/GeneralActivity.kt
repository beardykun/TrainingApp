package com.iamagamedev.trainingapp.ui.general

import android.content.Intent
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.iamagamedev.trainingapp.R
import kotlinx.android.synthetic.main.activity_general.*
import org.jetbrains.anko.find

abstract class GeneralActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        val layoutInflater: LayoutInflater = layoutInflater

        val container = layoutInflater.inflate(R.layout.activity_general, window.decorView as ViewGroup, false)
        layoutInflater.inflate(layoutResID, container.findViewById(R.id.cont_root) as ViewGroup, true)
        super.setContentView(container)
        progressLay.visibility = View.GONE
    }

    fun showErrorSnack(error: String) {
        showErrorSnack(error, coordinatorLayout)
    }

    private fun showErrorSnack(error: String, coordinatorLayout: CoordinatorLayout?) {
        val snackbar = Snackbar
                .make(coordinatorLayout!!, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.app_error_dismiss) { }

        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.black))

        val sbView = snackbar.view
        val textView = sbView.find<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.black))
        textView.maxLines = 10
        snackbar.show()
    }

    fun showProgressView() {
        progressLay.visibility = View.VISIBLE
    }

    fun hideProgressView() {
        progressLay.visibility = View.GONE
    }

    protected fun startActivity(activityClass: Class<*>) {
        startActivity(activityClass, false)
    }

    protected fun startActivity(activityClass: Class<*>, lockBackAction: Boolean) {
        val intent = Intent(this, activityClass)
        if (lockBackAction) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}
