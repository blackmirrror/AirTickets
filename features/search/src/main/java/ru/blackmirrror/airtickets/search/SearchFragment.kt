package ru.blackmirrror.airtickets.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.blackmirrror.airtickets.common.SearchNavigationHandler
import ru.blackmirrror.airtickets.search.databinding.FragmentSearchBinding

class SearchFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchBinding
    private var searchNavigationHandler: SearchNavigationHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchNavigationHandler = activity as? SearchNavigationHandler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setUpRecycler()

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUpRecycler() {
        val placesAdapter = PlacesAdapter()
        placesAdapter.submitList(
            listOf(
                Place(
                    1,
                    requireContext().getDrawable(R.drawable.place1),
                    getString(R.string.place_one)
                ),
                Place(
                    2,
                    requireContext().getDrawable(R.drawable.place2),
                    getString(R.string.place_two)
                ),
                Place(
                    3,
                    requireContext().getDrawable(R.drawable.place3),
                    getString(R.string.place_three)
                )
            )
        )
        placesAdapter.onPlaceItemClickListener = {
            binding.placesSearch.searchToPlaces.setText(it.town)
        }
        binding.recyclerPlaces.adapter = placesAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpButtons()
        setUpEditText()

//        binding.simpleImage.setOnClickListener {
//            searchNavigationHandler?.actionSearchFragmentToPlugSearchFragment()
//            //dismiss()
//        }
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
                binding.placesSearch.searchToPlaces.setText(R.string.place_default)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpEditText() {
        with(binding.placesSearch.searchToPlaces) {
            setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    val drawableEnd = this.compoundDrawablesRelative[2]
                    if (drawableEnd != null) {
                        val drawableWidth = drawableEnd.bounds.width()
                        val editTextWidth = this.width
                        val editTextPaddingEnd = this.paddingEnd
                        val touchX = event.x

                        if (touchX >= editTextWidth - editTextPaddingEnd - drawableWidth) {
                            this.setText("")
                            return@setOnTouchListener true
                        }
                    }
                }
                false
            }
        }
    }

    private fun onPlugFragment() {
        searchNavigationHandler?.actionSearchFragmentToPlugSearchFragment()
        dismiss()
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}