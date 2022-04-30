package com.sara.waie.androidbasicsetup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sara.waie.androidbasicsetup.databinding.RvOrderHistoryItemBinding
import com.sara.waie.androidbasicsetup.model.Order
import kotlinx.coroutines.CoroutineDispatcher

class RecentOrderHistoryPagingAdapter() :
    PagingDataAdapter<Order, RecentOrderHistoryPagingAdapter.HisToryViewHolder>(
        OrderDiffCallBack()
    ) {

    class HisToryViewHolder(val binding: RvOrderHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.order = order
        }
    }

    class OrderDiffCallBack : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: HisToryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HisToryViewHolder {
        return HisToryViewHolder(
            RvOrderHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}