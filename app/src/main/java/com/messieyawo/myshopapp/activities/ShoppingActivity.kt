package com.messieyawo.myshopapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.messieyawo.myshopapp.R
import com.messieyawo.myshopapp.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {
    lateinit var binding: ActivityShoppingBinding
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.shoppingHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation

        bottomNavigationView.setupWithNavController(navController)

    }
}