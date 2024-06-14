package ru.blackmirrror.airtickets.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.blackmirrror.airtickets.common.BottomNavigationVisibilityManager
import ru.blackmirrror.airtickets.main.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var bottomNavigationManager: BottomNavigationVisibilityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationManager = activity as? BottomNavigationVisibilityManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        bottomNavigationManager?.showBottomNavigationBar()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigationManager = null
    }
}