package ru.blackmirrror.airtickets.ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.blackmirrror.airtickets.ticket.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

    private lateinit var binding: FragmentTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }
}