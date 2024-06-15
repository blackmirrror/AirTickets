package ru.blackmirrror.airtickets

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.blackmirrror.airtickets.common.BottomNavigationVisibilityManager
import ru.blackmirrror.airtickets.common.SearchNavigationHandler
import ru.blackmirrror.airtickets.databinding.ActivityMainBinding
import ru.blackmirrror.airtickets.search.SearchFragment


class MainActivity : AppCompatActivity(), BottomNavigationVisibilityManager,
    SearchNavigationHandler {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        setNavigation()
    }

    private fun setNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun showBottomNavigationBar() {
        binding.separator.visibility = View.VISIBLE
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    override fun hideBottomNavigationBar() {
        binding.separator.visibility = View.GONE
        binding.bottomNavigation.visibility = View.GONE
    }

    override fun actionMainFragmentToSearchFragment() {
        val searchFragment = SearchFragment()
        searchFragment.show(supportFragmentManager, SearchFragment.TAG)
    }

    override fun actionSearchFragmentToPlugSearchFragment() {
        navController.navigate(R.id.action_mainFragment_to_plugNavGraph)
    }
}