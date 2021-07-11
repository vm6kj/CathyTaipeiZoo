package com.kc_hsu.cathaytpezoo.ui.areaplant

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kc_hsu.cathaytpezoo.R
import com.kc_hsu.cathaytpezoo.base.BaseFragment
import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import com.kc_hsu.cathaytpezoo.databinding.AreaPlantFragmentBinding
import com.kc_hsu.cathaytpezoo.ui.TpeZooClickListener
import com.kc_hsu.cathaytpezoo.ui.plantdetail.PlantDetailFragment
import com.kc_hsu.cathaytpezoo.utils.openFragment
import timber.log.Timber

class AreaPlantFragment : BaseFragment<AreaPlantFragmentBinding>(), AreaPlantContract.View,
    TpeZooClickListener {

    companion object {
        private const val ARGUMENT_ZOO_AREA = "zoo_area"

        fun newInstance(zooAreaItem: ZooAreaResponseBody.Result.ResultItem): AreaPlantFragment {
            // Maybe the modern way of passing data between fragments would be FragmentManager?
            // https://developer.android.com/guide/fragments/communicate
            val fragment = AreaPlantFragment()
            fragment.arguments = bundleOf(ARGUMENT_ZOO_AREA to zooAreaItem)
            return fragment
        }
    }

    override fun getLayoutId() = R.layout.area_plant_fragment

    private lateinit var presenter: AreaPlantPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

        arguments?.get(ARGUMENT_ZOO_AREA)?.apply {
            if (this is ZooAreaResponseBody.Result.ResultItem) {
                presenter = AreaPlantPresenter(this@AreaPlantFragment, this)
            } else {
                Timber.e(IllegalArgumentException(), "Failed to get the item zoo area")
            }
        }

        presenter.subscribe(this)

        binding.areaPlantListLayout.retryViewLayout.cvRetry.setOnClickListener {
            presenter.loadAreaPlant()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showZooAreaDetailInfo(zooArea: ZooAreaResponseBody.Result.ResultItem) {
        binding.zooArea = zooArea
    }

    override fun showRelatedPlantList(list: List<AreaPlantResponseBody.Result.ResultItem>) {
        if (list.isEmpty()) {
            binding.areaPlantListLayout.isNoPlant = true
            return
        }

        // Filter out duplicated element
        val filterList = list.distinctBy { it.FNameCh }

        with(binding.areaPlantListLayout.rvPlantArea) {
            ViewCompat.setNestedScrollingEnabled(this, false)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = AreaPlantItemRecyclerViewAdapter(filterList, this@AreaPlantFragment)
        }
    }

    override fun setLoadingView(isLoading: Boolean) {
        binding.areaPlantListLayout.isLoading = isLoading
    }

    override fun setLoadDataErrorView(isError: Boolean) {
        binding.areaPlantListLayout.isError = isError
    }

    override fun onListItemClicked(item: Any?) {
        if (item is AreaPlantResponseBody.Result.ResultItem) {
            (activity as? AppCompatActivity)?.openFragment(
                PlantDetailFragment.newInstance(item),
                R.id.contentFrame,
                true
            )
        }
    }
}