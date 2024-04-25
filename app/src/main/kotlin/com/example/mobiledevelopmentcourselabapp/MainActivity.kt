package com.example.mobiledevelopmentcourselabapp

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mobiledevelopmentcourselabapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navView: BottomNavigationView? = binding?.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Добавлявать новые элементы меню по их id
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_article,
                R.id.navigation_second,
                R.id.navigation_third
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController)

        Log.d("Lifecycles", "${this::class.simpleName} - onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycles", "${this::class.simpleName} - onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycles", "${this::class.simpleName} - onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycles", "${this::class.simpleName} - onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycles", "${this::class.simpleName} - onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycles", "${this::class.simpleName} - onDestroy")

    }
}