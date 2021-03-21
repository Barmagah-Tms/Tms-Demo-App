package com.barmagah.tms_demo.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class BindingCustomSetters {

    companion object {
        const val TAG = "BINDING.."
    }

}

@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("imgSrc")
fun setImgDrawableSrc(view: ImageView, resId: Int?) {
    view.setImageDrawable(view.context.getDrawable(resId!!))
}
