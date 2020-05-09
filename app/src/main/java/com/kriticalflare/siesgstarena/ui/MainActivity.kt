package com.kriticalflare.siesgstarena.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        setupNav()
    }

    private fun setupNav(){
        val navController = findNavController(R.id.fragNavHost)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.contestsFragment,
                R.id.problemsFragment,
                R.id.usersFragment
            )
        )
        binding.bottomNavView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)

//        Prevent fragment recreation on selecting the same fragment again
        binding.bottomNavView.setOnNavigationItemReselectedListener {
//            Todo: Maybe implement scroll to top of list, issue is how do we the fragment instance
//             to access the recyclerview or do we have a shared viewmodel which fires an event
        }
    }
}
