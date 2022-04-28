package com.sara.waie.androidbasicsetup.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sara.waie.androidbasicsetup.databinding.RvHomeSalestodayItemBinding
import com.sara.waie.androidbasicsetup.model.Post

class SalesTodayAdapter : RecyclerView.Adapter<SalesTodayAdapter.SalesTodayAdapterViewHolder>() {
    private val TAG="SalesTodayAdapter"
    private var saleList=ArrayList<Post>()
    public fun updateList(newList:ArrayList<Post>){
        Log.d(TAG,"updating List "+newList.toString())
        saleList=newList
        notifyDataSetChanged()
    }

    class SalesTodayAdapterViewHolder(val binding: RvHomeSalestodayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun  bindMyData(post: Post){
            binding.post=post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesTodayAdapterViewHolder {
       return SalesTodayAdapterViewHolder(RvHomeSalestodayItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SalesTodayAdapterViewHolder, position: Int) {
    holder.bindMyData(saleList.get(position))
    }

    override fun getItemCount(): Int {
     return saleList.size
    }
}