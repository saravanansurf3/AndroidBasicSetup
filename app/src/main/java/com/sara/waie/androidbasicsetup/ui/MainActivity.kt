package com.sara.waie.androidbasicsetup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sara.waie.androidbasicsetup.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val controller=findNavController(R.id.nav_host_fragment)
        val bottomNavMenu=findViewById<BottomNavigationView>(R.id.bottom_nav_menu)
        bottomNavMenu.setupWithNavController(controller)
    }
}