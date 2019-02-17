package com.iamagamedev.trainingapp.ui.general

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.achievements.AchievementsActivity
import com.iamagamedev.trainingapp.ui.statistics.StatisticsActivity
import com.iamagamedev.trainingapp.ui.story.StoryActivity
import com.iamagamedev.trainingapp.ui.training.TrainingActivity
import kotlinx.android.synthetic.main.activity_general_with_menu.*
import kotlinx.android.synthetic.main.toolbar.*


abstract class GeneralActivityWithMenu : GeneralActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        val layoutInflater = layoutInflater

        val container = layoutInflater.inflate(R.layout.activity_general_with_menu, window.decorView as ViewGroup, false)

        layoutInflater.inflate(layoutResID, container.findViewById(R.id.cont_root) as ViewGroup, true)
        super.setContentView(container)


        progressLay.visibility = View.GONE

        setToolbar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        setDrawer(toolbar, drawer, navigationView)
    }

    private fun setDrawer(toolbar: Toolbar, drawer: DrawerLayout, navigationView: NavigationView) {
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_navigation_drawer_open, R.string.app_navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        val bar = supportActionBar
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false)
            bar.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val activity: Activity = when (item.itemId) {
            R.id.achievements_menu -> AchievementsActivity()

            R.id.story_menu -> StoryActivity()

            R.id.statistics_menu -> StatisticsActivity()

            R.id.training_choice_menu -> TrainingActivity()

            else -> TrainingActivity()

        }
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
