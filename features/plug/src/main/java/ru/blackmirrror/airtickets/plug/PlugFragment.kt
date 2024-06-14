package ru.blackmirrror.airtickets.plug

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.blackmirrror.airtickets.common.BottomNavigationVisibilityManager

class PlugFragment : Fragment() {
    private var bottomNavigationManager: BottomNavigationVisibilityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationManager = activity as? BottomNavigationVisibilityManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_plug, container, false)
        bottomNavigationManager?.showBottomNavigationBar()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigationManager = null
    }
}