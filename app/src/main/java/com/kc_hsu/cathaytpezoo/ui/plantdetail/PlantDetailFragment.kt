package com.kc_hsu.cathaytpezoo.ui.plantdetail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.kc_hsu.cathaytpezoo.R
import com.kc_hsu.cathaytpezoo.base.BaseFragment
import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.databinding.PlantDetailFragmentBinding
import timber.log.Timber

class PlantDetailFragment : BaseFragment<PlantDetailFragmentBinding>() {
    override fun getLayoutId(): Int = R.layout.plant_detail_fragment

    companion object {
        private const val ARGUMENT_AREA_PLANT = "area_plant"

        fun newInstance(areaPlantItem: AreaPlantResponseBody.Result.ResultItem): PlantDetailFragment {
            val fragment = PlantDetailFragment()
            fragment.arguments = bundleOf(ARGUMENT_AREA_PLANT to areaPlantItem)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

        arguments?.get(ARGUMENT_AREA_PLANT)?.apply {
            if (this is AreaPlantResponseBody.Result.ResultItem) {
                binding.areaPlant = this
            } else {
                Timber.e(IllegalArgumentException(), "Failed to get the item area plant")
            }

        }
    }
}