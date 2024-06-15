package ru.blackmirrror.airtickets.plug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import ru.blackmirrror.airtickets.common.BottomNavigationVisibilityManager
import ru.blackmirrror.airtickets.plug.databinding.FragmentPlugSearchTwoBinding

class PlugSearchTwoFragment : Fragment() {

    private lateinit var binding: FragmentPlugSearchTwoBinding
    private var bottomNavigationManager: BottomNavigationVisibilityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationManager = activity as? BottomNavigationVisibilityManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlugSearchTwoBinding.inflate(inflater, container, false)
        bottomNavigationManager?.hideBottomNavigationBar()
        setUpBack()
        return binding.root
    }

    private fun setUpBack() {
        binding.plugBtnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigationManager = null
    }
}