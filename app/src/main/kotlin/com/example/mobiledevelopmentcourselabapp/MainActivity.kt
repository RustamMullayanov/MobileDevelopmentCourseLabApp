package com.example.mobiledevelopmentcourselabapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mobiledevelopmentcourselabapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navView: BottomNavigationView? = binding?.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val mainTabsSet = setOf(
            R.id.navigation_article,
            R.id.navigation_list,
            R.id.navigation_video
        )

        // Добавлявать новые элементы меню по их id
        val appBarConfiguration = AppBarConfiguration(mainTabsSet)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding?.navView?.isVisible = destination.id in mainTabsSet
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController)
    }

    // Main Activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return false
    }
}