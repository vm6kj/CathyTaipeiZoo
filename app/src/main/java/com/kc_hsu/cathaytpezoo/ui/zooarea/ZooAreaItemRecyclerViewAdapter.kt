package com.kc_hsu.cathaytpezoo.ui.zooarea

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import com.kc_hsu.cathaytpezoo.databinding.ZooAreaItemBinding
import com.kc_hsu.cathaytpezoo.ui.TpeZooClickListener

class ZooAreaItemRecyclerViewAdapter(
    private val list: List<ZooAreaResponseBody.Result.ResultItem>,
    private val clickListener: TpeZooClickListener
) : RecyclerView.Adapter<ZooAreaItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ZooAreaItemBinding.inflate(
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

    inner class ViewHolder(private val binding: ZooAreaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(zooArea: ZooAreaResponseBody.Result.ResultItem) {
            binding.zooArea = zooArea
            binding.clickListener = clickListener
        }
    }
}