package com.sara.waie.androidbasicsetup.ui.adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sara.waie.androidbasicsetup.R
import java.lang.Exception

object UiBindingAdapter {

    @BindingAdapter("price", "currency")
    @JvmStatic
    fun setPriceWithCurrency(view: AppCompatTextView, price: Float, currency: String) {
        view.text=currency+" "+price.toString()
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImageUrl(view: AppCompatImageView,imageUrl:String?){
        imageUrl?.let {
            Glide
                .with(view.context)
                .load(it)
//                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(view);
        }

    }

    @BindingAdapter("lable","lableValue")
    @JvmStatic
    fun updateLableValue(view: AppCompatTextView,lable:String?,lableValue:String?){
        try {
            val content= lable+" : "+lableValue.toString()
            view.text=content
        }catch (exception:Exception){

        }

    }
}