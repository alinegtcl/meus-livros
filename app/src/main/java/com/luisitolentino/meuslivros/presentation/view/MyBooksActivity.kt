package com.luisitolentino.meuslivros.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.luisitolentino.meuslivros.R
import com.luisitolentino.meuslivros.databinding.ActivityMyBooksBinding

class MyBooksActivity : AppCompatActivity() {
    private val binding: ActivityMyBooksBinding by lazy {
        ActivityMyBooksBinding.inflate(layoutInflater)
    }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMyBooks)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerMyBooks) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerMyBooks)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}