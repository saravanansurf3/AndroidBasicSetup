package com.sara.waie.androidbasicsetup.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sara.waie.androidbasicsetup.R
import com.sara.waie.androidbasicsetup.viewmodel.DashboardViewmodel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: DashboardViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val controller=findNavController(R.id.nav_host_fragment)
        val bottomNavMenu=findViewById<BottomNavigationView>(R.id.bottom_nav_menu)
        bottomNavMenu.setupWithNavController(controller)
    }
}