package com.sara.waie.androidbasicsetup.ui.adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sara.waie.androidbasicsetup.R

object UiBindingAdapter {

    @BindingAdapter("price", "currency")
    @JvmStatic
    fun setPriceWithCurrency(view: AppCompatTextView, price: String, currency: String) {
        view.text=currency+" "+price
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImageUrl(view: AppCompatImageView,imageUrl:String){
        imageUrl?.let {
            Glide
                .with(view.context)
                .load(it)
//                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(view);
        }

    }
}