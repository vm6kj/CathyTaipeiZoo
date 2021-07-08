package com.kc_hsu.cathaytpezoo.utils

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kc_hsu.cathaytpezoo.R
import com.kc_hsu.cathaytpezoo.TpeZooApplication

object BindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeholder", "error"])
    fun bindLoadImage(
        imageView: ImageView,
        imageUrl: String?,
        placeholder: Drawable?,
        error: Drawable?
    ) {
        Glide.with(imageView)
            .load(imageUrl)
            .placeholder(placeholder)
            .error(error)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("hyperLink")
    fun bindHyperLink(textView: TextView, url: String?) {
        if (url != null) {
            val builder = SpannableStringBuilder(textView.context.getString(R.string.browse))
            builder.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val context = TpeZooApplication.applicationContext()
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            }, 0, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            textView.text = builder
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.removeLinksUnderline()
        } else {
            textView.text = ""
        }
    }
}