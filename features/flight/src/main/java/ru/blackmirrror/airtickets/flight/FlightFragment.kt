package ru.blackmirrror.airtickets.flight

import android.app.DatePickerDialog
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
import ru.blackmirrror.airtickets.common.utils.EditTextUtils
import ru.blackmirrror.airtickets.common.utils.TextFormatter.getDayOfWeek
import ru.blackmirrror.airtickets.common.utils.TextFormatter.getMonthName
import ru.blackmirrror.airtickets.data.models.Flight
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.flight.databinding.FragmentFlightBinding
import java.util.Calendar
import java.util.Date
import ru.blackmirrror.airtickets.common.R as CommonR

class FlightFragment : Fragment() {

    private lateinit var binding: FragmentFlightBinding
    private var navigationHandler: NavigationHandler? = null

    private val viewModel by viewModel<FlightViewModel>()
    private lateinit var flightAdapter: FlightAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationHandler = activity as? NavigationHandler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightBinding.inflate(inflater, container, false)

        setSearchOptions()
        setButtonsOptions()
        setUpRecycler()
        collectFlights()
        collectDates()

        return binding.root
    }

    private fun setSearchOptions() {
        initSearch()

        binding.flightSearch.flightBtnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        with(binding.flightSearch.flightEtSearchTo) {
            setOnTouchListener(
                EditTextUtils.ClearTextOnTouchListener(this) {
                    this.setText("")
                })
        }

        with(binding.flightSearch.flightEtSearchFrom) {
            setOnTouchListener(
                EditTextUtils.ClearTextOnTouchListener(this) {
                    val from = this.text
                    this.text = binding.flightSearch.flightEtSearchTo.text
                    binding.flightSearch.flightEtSearchTo.text = from
                })
        }
    }

    private fun initSearch() {
        val from = arguments?.getString(getString(CommonR.string.arg_name_search_from))
        val to = arguments?.getString(getString(CommonR.string.arg_name_search_to))
        binding.flightSearch.flightEtSearchFrom.setText(from)
        binding.flightSearch.flightEtSearchTo.setText(to)
    }

    private fun setUpRecycler() {
        flightAdapter = FlightAdapter()
        binding.flightRecycler.adapter = flightAdapter
    }

    private fun collectFlights() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flights.collect {
                    handleResult(it)
                }
            }
        }
    }

    private fun collectDates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.departureDate.collect {
                    binding.flightButtons.flightDepartureDate.text =
                        getSelectedDate(it ?: Date())
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.arrivalDate.collect {
                    if (it != null)
                        binding.flightButtons.flightReturnDate.text = getSelectedDate(it)
                }
            }
        }
    }

    private fun handleResult(resultState: ResultState<List<Flight>>) {
        when (resultState) {
            is ResultState.Success -> {
                flightAdapter.submitList(resultState.data)
                binding.flightProgress.visibility = View.GONE
            }

            is ResultState.Loading -> {
                binding.flightProgress.visibility = View.VISIBLE
            }

            is ResultState.Error -> {
                binding.flightProgress.visibility = View.GONE
            }
        }
    }

    private fun setButtonsOptions() {
        with(binding.flightButtons) {
            flightReturnDate.setOnClickListener {
                showDatePickerDialog { date -> viewModel.setArrivalDate(date) }
            }
            flightDepartureDate.setOnClickListener {
                showDatePickerDialog { date -> viewModel.setDepartureDate(date) }
            }
        }
        binding.flightButtonViewAll.setOnClickListener {
            navigationHandler?.actionFlightFragmentToTicketFragment(
                from = binding.flightSearch.flightEtSearchFrom.text.toString(),
                to = binding.flightSearch.flightEtSearchTo.text.toString(),
                date = viewModel.departureDate.value ?: Date(),
                countPassengers = 1
            )
        }
    }

    private fun showDatePickerDialog(action: (date: Date) -> Unit) {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            requireContext(), R.style.DatePickerDialog, { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }.time
                action(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun getSelectedDate(date: Date): String {
        val calendar = Calendar.getInstance().apply { time = date }
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        return "$day ${getMonthName(month)}, ${getDayOfWeek(year, month, day)}"
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationHandler = null
    }

}