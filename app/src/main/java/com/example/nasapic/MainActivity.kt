package com.example.nasapic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.nasapic.ui.earth_image.EarthImageFragment
import com.example.nasapic.ui.main.MainFragment
import com.example.nasapic.ui.notes.RecyclerNotesFragment
import com.example.nasapic.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        val bottomMenu: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val homeFragment = MainFragment()
        val settingsFragment = SettingsFragment()
        val earthFragment = EarthImageFragment()
        val recyclerNotesFragment = RecyclerNotesFragment()

        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> setCurrentFragment(homeFragment)
                R.id.menu_settings -> setCurrentFragment(settingsFragment)
                R.id.menu_earth -> setCurrentFragment(earthFragment)
                R.id.menu_notes -> setCurrentFragment(recyclerNotesFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out
            )
            replace(R.id.container, fragment)
            commit()
        }
    }
}