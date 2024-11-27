package com.aurea.batikcam.ui
import android.content.Context
import android.widget.ImageView
import com.aurea.batikcam.R
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.BlurTransformation
class UIUtility {
    companion object {
        fun applyBlur(context: Context, imageView: ImageView) {
            Glide.with(context)
                .load(R.drawable.bg_home)
                .transform(BlurTransformation(25))
                .into(imageView)
        }
    }
}