package ru.blackmirrror.airtickets.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.airtickets.common.BottomNavigationVisibilityManager
import ru.blackmirrror.airtickets.data.models.NoConnection
import ru.blackmirrror.airtickets.data.models.Offer
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError
import ru.blackmirrror.airtickets.main.databinding.FragmentMainBinding
import ru.blackmirrror.airtickets.main.utils.HorizontalItemDecoration
import ru.blackmirrror.airtickets.common.R as CommonR


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var bottomNavigationManager: BottomNavigationVisibilityManager? = null

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var offerAdapter: OfferAdapter

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

        setUpRecycler()
        collectOffers()
        setUpSendFrom()

        return binding.root
    }

    private fun setUpRecycler() {
        offerAdapter = OfferAdapter()
        binding.recyclerMusic.adapter = offerAdapter
        val space = resources.getDimensionPixelSize(R.dimen.item_space)
        binding.recyclerMusic.addItemDecoration(HorizontalItemDecoration(space))
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
                    is NoConnection -> showToast(getString(R.string.error_no_connection))
                    is ServerError -> showToast(getString(R.string.error_server))
                }
            }
        }
    }

    private fun setUpSendFrom() {
        binding.searchLayout.searchFrom.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    //todo
                    true
                }
                else -> false
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigationManager = null
    }
}