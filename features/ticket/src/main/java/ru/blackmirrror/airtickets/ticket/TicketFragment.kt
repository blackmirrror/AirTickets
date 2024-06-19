package ru.blackmirrror.airtickets.ticket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.airtickets.common.NavigationHandler
import ru.blackmirrror.airtickets.common.utils.TextFormatter
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.Ticket
import ru.blackmirrror.airtickets.ticket.databinding.FragmentTicketBinding
import java.util.Date
import ru.blackmirrror.airtickets.common.R as CommonR

class TicketFragment : Fragment() {

    private lateinit var binding: FragmentTicketBinding
    private var navigationHandler: NavigationHandler? = null

    private val viewModel by viewModel<TicketViewModel>()
    private lateinit var ticketAdapter: TicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationHandler = activity as? NavigationHandler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketBinding.inflate(inflater, container, false)

        initHeader()
        setUpButtons()
        setUpRecycler()
        collectTickets()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initHeader() {
        val from = arguments?.getString(getString(CommonR.string.arg_name_search_from))
        val to = arguments?.getString(getString(CommonR.string.arg_name_search_to))
        val date = arguments?.getLong(getString(CommonR.string.arg_name_date)) ?: DEFAULT_DATE
        val countPassengers =
            arguments?.getInt(getString(CommonR.string.arg_name_count_passengers))
                ?: DEFAULT_COUNT_PASSENGERS
        with(binding.ticketHeader) {
            ticketPath.text = "$from - $to"
            ticketData.text = TextFormatter.toDateWithPassengers(Date(date), countPassengers)
        }
    }

    private fun setUpButtons() {
        binding.ticketHeader.ticketBtnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun setUpRecycler() {
        ticketAdapter = TicketAdapter()
        binding.ticketRecycler.adapter = ticketAdapter
    }

    private fun collectTickets() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tickets.collect {
                    handleResult(it)
                }
            }
        }
    }

    private fun handleResult(resultState: ResultState<List<Ticket>>) {
        when (resultState) {
            is ResultState.Success -> {
                ticketAdapter.submitList(resultState.data)
                binding.ticketProgress.visibility = View.GONE
            }

            is ResultState.Loading -> {
                binding.ticketProgress.visibility = View.VISIBLE
            }

            is ResultState.Error -> {
                binding.ticketProgress.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationHandler = null
    }

    companion object {
        private const val DEFAULT_COUNT_PASSENGERS = 1
        private val DEFAULT_DATE = Date().time
    }
}