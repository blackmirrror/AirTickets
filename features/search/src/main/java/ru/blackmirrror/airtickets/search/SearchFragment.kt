package ru.blackmirrror.airtickets.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.airtickets.common.utils.EditTextUtils
import ru.blackmirrror.airtickets.common.NavigationHandler
import ru.blackmirrror.airtickets.search.databinding.FragmentSearchBinding
import ru.blackmirrror.airtickets.common.R as CommonR
import ru.blackmirrror.airtickets.data.R as DataR

class SearchFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModel<PlacesViewModel>()
    private var navigationHandler: NavigationHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationHandler = activity as? NavigationHandler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setUpRecycler()
        setUpButtons()
        setSearchOptions()

        return binding.root
    }

    private fun setUpRecycler() {
        val placesAdapter = PlacesAdapter()
        placesAdapter.submitList(
            viewModel.places
        )
        placesAdapter.onPlaceItemClickListener = {
            binding.placesSearch.placesSearchTo.setText(it.town)
        }
        binding.placesRecycler.adapter = placesAdapter
    }

    private fun setUpButtons() {
        with(binding.placesBottomGroup) {
            searchBtnPath.setOnClickListener {
                onPlugFragment()
            }
            searchBtnFire.setOnClickListener {
                onPlugFragment()
            }
            searchBtnCalendar.setOnClickListener {
                onPlugFragment()
            }
            searchBtnBall.setOnClickListener {
                binding.placesSearch.placesSearchTo.setText(DataR.string.place_default)
            }
        }
    }

    private fun setSearchOptions() {
        initSearch()

        with(binding.placesSearch.placesSearchTo) {
            setOnTouchListener(EditTextUtils.ClearTextOnTouchListener(this) {
                this.text = null
            })

            setOnEditorActionListener(EditTextUtils.OnEditorActionDoneListener {
                if (this.text.isNotEmpty())
                    navigationHandler?.actionSearchFragmentToFlightFragment(
                        from = binding.placesSearch.placesSearchFrom.text.toString(),
                        to = this.text.toString()
                    )
            })
        }
    }

    private fun initSearch() {
        val from = arguments?.getString(getString(CommonR.string.arg_name_search_from))
        val to = arguments?.getString(getString(CommonR.string.arg_name_search_to))
        binding.placesSearch.placesSearchFrom.setText(from)
        binding.placesSearch.placesSearchTo.setText(to)
    }

    private fun onPlugFragment() {
        navigationHandler?.actionSearchFragmentToPlugSearchFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationHandler = null
    }
}