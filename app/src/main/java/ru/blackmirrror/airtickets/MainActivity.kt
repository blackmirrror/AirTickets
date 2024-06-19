package ru.blackmirrror.airtickets

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.blackmirrror.airtickets.common.NavigationHandler
import ru.blackmirrror.airtickets.databinding.ActivityMainBinding
import ru.blackmirrror.airtickets.flight.FlightFragmentDirections
import ru.blackmirrror.airtickets.main.MainFragmentDirections
import ru.blackmirrror.airtickets.search.SearchFragmentDirections
import java.util.Date


class MainActivity : AppCompatActivity(), NavigationHandler {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

    override fun actionMainFragmentToSearchFragment(from: String) {
        val action = MainFragmentDirections.actionMainFragmentToSearchNavGraph(
            from
        )
        navController.navigate(action)
    }

    override fun actionSearchFragmentToPlugSearchFragment() {
        navController.navigate(R.id.action_searchFragment_to_plugFragment)
    }

    override fun actionSearchFragmentToFlightFragment(from: String, to: String) {
        val action = SearchFragmentDirections.actionSearchFragmentToFlightFragment(
            from, to
        )
        navController.navigate(action)
    }

    override fun actionFlightFragmentToTicketFragment(
        from: String,
        to: String,
        date: Date,
        countPassengers: Int
    ) {
        val action = FlightFragmentDirections.actionFlightFragmentToTicketFragment(
            from, to, date.time, countPassengers
        )
        navController.navigate(action)
    }
}