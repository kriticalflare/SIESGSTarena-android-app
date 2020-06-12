package com.kriticalflare.siesgstarena.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        setupNav()
    }

    private fun setupNav() {
        val navController = findNavController(R.id.fragNavHost)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.contestsFragment,
                R.id.problemsFragment,
                R.id.blogsFragment,
                R.id.appsFragment,
                R.id.usersFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//            Todo: Maybe implement scroll to top of list, issue is how do we the fragment instance
//             to access the recyclerview or do we have a shared viewmodel which fires an event
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        binding.bottomNavView.setupWithNavController(menu!!, findNavController(R.id.fragNavHost))
        return true
    }
}
