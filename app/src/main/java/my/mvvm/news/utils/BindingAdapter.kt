package my.mvvm.news.utils

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import my.mvvm.news.R
import my.mvvm.news.data.model.Article

class BindingAdapter {
    companion object {
        @BindingAdapter(value = ["android:image"])
        @JvmStatic
        fun setNewsImage(imageView: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(imageView)
                    .load(it)
                    .placeholder(R.drawable.placeholder)
                    .into(imageView)
            }
        }

        @BindingAdapter(value = ["android:source"])
        @JvmStatic
        fun setNewsSource(textView: TextView, article: Article) {
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.isClickable = true
            val text = "<a href='${article.url}'> ${article.source.sourceName} </a>"
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                textView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
            }
        }

        @BindingAdapter(value = ["android:date"])
        @JvmStatic
        fun setNewsDate(textView: TextView, publishedAt: String) {
            val date = publishedAt.substring(0, 10)
            textView.text = date
        }

    }


}