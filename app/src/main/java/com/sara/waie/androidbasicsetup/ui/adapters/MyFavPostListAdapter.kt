package com.sara.waie.androidbasicsetup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.sara.waie.androidbasicsetup.databinding.RvMyfavItemBinding
import com.sara.waie.androidbasicsetup.model.MyFavPost

class MyFavPostListAdapter: PagingDataAdapter<MyFavPost, MyFavPostListAdapter.ViewHolder>(DiffCallBack()) {

    class ViewHolder(val binding: RvMyfavItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myFavPost: MyFavPost){
            binding.myfavPost=myFavPost
        }
    }

   class DiffCallBack: DiffUtil.ItemCallback<MyFavPost>() {
       override fun areItemsTheSame(oldItem: MyFavPost, newItem: MyFavPost): Boolean {
          return oldItem.itemId==newItem.itemId
       }

       override fun areContentsTheSame(oldItem: MyFavPost, newItem: MyFavPost): Boolean {
          return oldItem==newItem
       }
   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(RvMyfavItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}