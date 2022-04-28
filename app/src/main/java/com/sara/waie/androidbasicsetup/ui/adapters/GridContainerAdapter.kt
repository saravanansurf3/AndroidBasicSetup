package com.sara.waie.androidbasicsetup.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sara.waie.androidbasicsetup.databinding.RvHorizontalContainerBinding

class GridContainerAdapter(private val adapter: SuggestionListAdapter) :
    RecyclerView.Adapter<GridContainerAdapter.HorizontalContainerViewHolder>() {

    class HorizontalContainerViewHolder(
        val binding: RvHorizontalContainerBinding,
        private val mContext: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(adapter: SuggestionListAdapter) {
            binding.listHorizontalContainer.apply {
                this.adapter = adapter
                this.layoutManager =
                    GridLayoutManager(mContext, 2,GridLayoutManager.HORIZONTAL,false)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalContainerViewHolder {
        return HorizontalContainerViewHolder(RvHorizontalContainerBinding.inflate(LayoutInflater.from(parent.context)),parent.context)

    }

    override fun onBindViewHolder(holder: HorizontalContainerViewHolder, position: Int) {
        holder.bind(adapter)
    }

    override fun getItemCount(): Int {
        return 1
    }
}