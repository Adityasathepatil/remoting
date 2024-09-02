package com.techtool.remoting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.techtool.remoting.UI.explore
import com.techtool.remoting.UI.home
import com.techtool.remoting.UI.internship
import com.techtool.remoting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.setBackgroundColor(getColor(android.R.color.white))

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        // Load the default fragment
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            home()
        ).commit()
    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.first_fragment -> selectedFragment = home()
                R.id.second_fragment -> selectedFragment = explore()
                R.id.third_fragment -> selectedFragment = internship()
            }

            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment!!
            ).commit()

            true
        }

}