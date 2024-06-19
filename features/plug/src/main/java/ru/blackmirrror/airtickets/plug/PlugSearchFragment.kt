package ru.blackmirrror.airtickets.plug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.blackmirrror.airtickets.plug.databinding.FragmentPlugSearchBinding

class PlugSearchFragment : Fragment() {

    private lateinit var binding: FragmentPlugSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlugSearchBinding.inflate(inflater, container, false)
        setUpBackButton()
        return binding.root
    }

    private fun setUpBackButton() {
        binding.plugBtnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }
}