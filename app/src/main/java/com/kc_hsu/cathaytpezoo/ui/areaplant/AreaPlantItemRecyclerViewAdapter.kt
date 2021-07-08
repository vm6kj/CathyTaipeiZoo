package com.kc_hsu.cathaytpezoo.ui.areaplant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.databinding.AreaPlantItemBinding
import com.kc_hsu.cathaytpezoo.ui.TpeZooClickListener

class AreaPlantItemRecyclerViewAdapter(
    private val list: List<AreaPlantResponseBody.Result.ResultItem>,
    private val clickListener: TpeZooClickListener
) : RecyclerView.Adapter<AreaPlantItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AreaPlantItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindTo(item)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: AreaPlantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(areaPlant: AreaPlantResponseBody.Result.ResultItem) {
            binding.areaPlant = areaPlant
            binding.clickListener = clickListener
        }
    }
}