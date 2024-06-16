package ru.blackmirrror.airtickets.flight

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.blackmirrror.airtickets.flight.databinding.FragmentFlightBinding
import java.util.Calendar

class FlightFragment : Fragment() {

    private lateinit var binding: FragmentFlightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setButtonsOptions() {
        with(binding.flightButtons) {
            flightReturnDate.setOnClickListener {
                showDatePickerDialog(flightDepartureDate)
            }
            flightDepartureDate.setOnClickListener {
                showDatePickerDialog(flightDepartureDate)
            }
        }
    }

    private fun showDatePickerDialog(button: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay ${getMonthName(selectedMonth)}, ${
                    getDayOfWeek(
                        selectedYear,
                        selectedMonth,
                        selectedDay
                    )
                }"
                button.text = selectedDate
            }, year, month, day)
        datePickerDialog.show()
    }

    private fun getMonthName(month: Int): String {
        val months = arrayOf(
            "янв",
            "фев",
            "мар",
            "апр",
            "май",
            "июн",
            "июл",
            "авг",
            "сен",
            "окт",
            "ноя",
            "дек"
        )
        return months[month]
    }

    private fun getDayOfWeek(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val days = arrayOf("вс", "пн", "вт", "ср", "чт", "пт", "сб")
        return days[calendar.get(Calendar.DAY_OF_WEEK) - 1]
    }

}