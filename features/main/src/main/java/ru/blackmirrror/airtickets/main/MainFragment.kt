package ru.blackmirrror.airtickets.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.airtickets.common.EditTextUtils
import ru.blackmirrror.airtickets.common.NavConstants.BUNDLE_NAME_STATE
import ru.blackmirrror.airtickets.common.NavConstants.RESULT_STATE_DIALOG
import ru.blackmirrror.airtickets.common.NavigationHandler
import ru.blackmirrror.airtickets.data.models.NoConnection
import ru.blackmirrror.airtickets.data.models.Offer
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError
import ru.blackmirrror.airtickets.main.databinding.FragmentMainBinding
import ru.blackmirrror.airtickets.main.utils.HorizontalItemDecoration
import ru.blackmirrror.airtickets.common.R as CommonR


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var navigationHandler: NavigationHandler? = null

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var offerAdapter: OfferAdapter

    private var stateDialog: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationHandler = activity as? NavigationHandler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setUpRecycler()
        collectOffers()
        setSearchOptions()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBottomSheetState()
    }

    private fun getBottomSheetState() {
        requireActivity().supportFragmentManager
            .setFragmentResultListener(RESULT_STATE_DIALOG, viewLifecycleOwner) { _, bundle ->
                stateDialog = bundle.getBoolean(BUNDLE_NAME_STATE)
            }
        if (stateDialog != null && stateDialog == true)
            navigateToSearch()
    }

    private fun setUpRecycler() {
        offerAdapter = OfferAdapter()
        binding.mainRecyclerMusic.adapter = offerAdapter
        binding.mainRecyclerMusic.addItemDecoration(
            HorizontalItemDecoration(resources.getDimensionPixelSize(R.dimen.item_space))
        )
    }

    private fun collectOffers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.offers.collect {
                    handleResult(it)
                }
            }
        }
    }

    private fun handleResult(resultState: ResultState<List<Offer>>) {
        when (resultState) {
            is ResultState.Success -> {
                offerAdapter.submitList(resultState.data)
                binding.mainProgress.visibility = View.GONE
            }

            is ResultState.Loading -> {
                binding.mainProgress.visibility = View.VISIBLE
            }

            is ResultState.Error -> {
                binding.mainProgress.visibility = View.GONE
                when (resultState.error) {
                    is NoConnection -> showToast(getString(CommonR.string.error_no_connection))
                    is ServerError -> showToast(getString(CommonR.string.error_server))
                }
            }
        }
    }

    private fun setSearchOptions() {
        binding.mainSearch.mainSearchFrom.setText(viewModel.getLastSearch())

        binding.mainSearch.mainSearchFrom.setOnEditorActionListener(EditTextUtils.OnEditorActionDoneListener {
            navigateToSearch()
        })
        with(binding.mainSearch.mainSearchTo) {
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    navigateToSearch()
                    clearFocus()
                }
            }
        }
    }

    private fun navigateToSearch() {
        val word = binding.mainSearch.mainSearchFrom.text.toString()
        if (word.isNotEmpty()) {
            viewModel.saveLastSearch(word)
            navigationHandler?.actionMainFragmentToSearchFragment(
                from = word
            )
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        navigationHandler = null
    }
}