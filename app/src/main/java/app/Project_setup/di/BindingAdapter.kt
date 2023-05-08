package app.Project_setup.di

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import app.Project_setup.R
import com.bumptech.glide.Glide
import java.io.File

@BindingAdapter("setSelectionCount")
fun setPostReactionsText(textView: TextView, item: MutableLiveData<Int>) {
    textView.text = item.value.toString()
}

@BindingAdapter("imageUrl", "withHolder", requireAll = false)
fun setImageUrl(imageView: ImageView, imageUrl: Any?, withHolder: Boolean = true) {
    try {
        imageUrl?.let {
            val source = when (it) {
                is File -> {
                    it
                }
                is Uri -> {
                    it
                }
                is Int -> {
                    it
                }
                is Drawable -> {
                    it
                }
                else -> {
                    it as String
                }
            }
            if (withHolder) {
                Glide.with(imageView)
                    .load(source)
                    .placeholder(R.drawable.logo)
                    .into(imageView).clearOnDetach()
            } else {
                Glide.with(imageView)
                    .load(source)
                    .into(imageView).clearOnDetach()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}




