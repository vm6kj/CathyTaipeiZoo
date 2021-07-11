package com.kc_hsu.cathaytpezoo.ui.zooarea

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kc_hsu.cathaytpezoo.R
import com.kc_hsu.cathaytpezoo.base.BaseFragment
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import com.kc_hsu.cathaytpezoo.databinding.ZooAreaItemListFragmentBinding
import com.kc_hsu.cathaytpezoo.ui.TpeZooClickListener
import com.kc_hsu.cathaytpezoo.ui.areaplant.AreaPlantFragment
import com.kc_hsu.cathaytpezoo.utils.openFragment
import com.kc_hsu.cathaytpezoo.utils.setupActionBar
import timber.log.Timber

/**
 * A fragment representing a list of Items.
 */
class ZooAreaListFragment : BaseFragment<ZooAreaItemListFragmentBinding>(), ZooAreaContract.View,
    TpeZooClickListener {

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() = ZooAreaListFragment()
    }

    override fun getLayoutId() = R.layout.zoo_area_item_list_fragment

    private val presenter by lazy { ZooAreaPresenter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setupActionBar(binding.toolbar) {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

        presenter.subscribe(this)
        binding.retryViewLayout.cvRetry.setOnClickListener {
            presenter.loadZooArea()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun setLoadingView(isLoading: Boolean) {
        binding.isLoading = isLoading
    }

    override fun setLoadDataErrorView(isError: Boolean) {
        binding.isError = isError
    }

    override fun showZooAreaList(list: List<ZooAreaResponseBody.Result.ResultItem>) {
        Timber.d("showZooAreaList")
        with(binding.rvZooArea) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter =
                ZooAreaItemRecyclerViewAdapter(list, this@ZooAreaListFragment)
        }
    }

    override fun onListItemClicked(item: Any?) {
        if (item is ZooAreaResponseBody.Result.ResultItem) {
            (activity as? AppCompatActivity)?.openFragment(
                AreaPlantFragment.newInstance(item),
                R.id.contentFrame,
                true
            )
        }
    }
}