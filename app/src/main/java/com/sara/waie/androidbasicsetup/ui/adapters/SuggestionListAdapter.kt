package com.sara.waie.androidbasicsetup.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sara.waie.androidbasicsetup.databinding.RvHomeSuggestionItemBinding
import com.sara.waie.androidbasicsetup.model.Post

class SuggestionListAdapter :
    RecyclerView.Adapter<SuggestionListAdapter.SuggestionListViewHolder>() {
    private var suggestionList = ArrayList<Post>()
    public fun updateSuggestion(newSuggestionList: ArrayList<Post>) {
        suggestionList = newSuggestionList
        notifyDataSetChanged()
    }

    class SuggestionListViewHolder(val binding: RvHomeSuggestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(post: Post) {
            binding.post = post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionListViewHolder {
        return SuggestionListViewHolder(
            RvHomeSuggestionItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: SuggestionListViewHolder, position: Int) {
        holder.bind(suggestionList.get(position))
    }

    override fun getItemCount(): Int {
        return suggestionList.size
    }
}