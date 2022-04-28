package com.sara.waie.androidbasicsetup.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sara.waie.androidbasicsetup.databinding.RvHomePopularsaleItemBinding
import com.sara.waie.androidbasicsetup.model.Post

class popularSaleAdapter : RecyclerView.Adapter<popularSaleAdapter.popularSaleViewHolder>() {
    private var popularList=ArrayList<Post>()

    fun updatePopularList(newList:ArrayList<Post>){
        popularList=newList
        notifyDataSetChanged()
    }
    class popularSaleViewHolder(val binding: RvHomePopularsaleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(post: Post){
            binding.post=post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): popularSaleViewHolder {
      return popularSaleViewHolder(RvHomePopularsaleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: popularSaleViewHolder, position: Int) {
        holder.bind(popularList.get(position))
    }

    override fun getItemCount(): Int {
       return popularList.size
    }
}