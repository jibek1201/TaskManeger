package com.example.taskmaneger

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmaneger.data.local.Pref
import com.example.taskmaneger.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val pref: Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.isShow())
            navController.navigate(R.id.onBoardingFragment)

        if (FirebaseAuth.getInstance().currentUser == null){
            navController.navigate(R.id.phoneFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.taskFragment
            )
        )

        val fragmentsWithoutNavView = setOf(R.id.onBoardingFragment, R.id.phoneFragment, R.id.verifyFragment, R.id.navigation_home, R.id.navigation_notifications)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (fragmentsWithoutNavView.contains(destination.id)) {
                navView.isVisible = false
                supportActionBar?.hide()
            } else {
                navView.isVisible = true
                supportActionBar?.show()
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}