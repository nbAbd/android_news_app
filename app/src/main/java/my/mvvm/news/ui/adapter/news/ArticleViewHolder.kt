package my.mvvm.news.ui.adapter.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.mvvm.news.data.model.Article
import my.mvvm.news.databinding.NewsSingleItemBinding

class ArticleViewHolder(private val binding: NewsSingleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article?, clickListener: OnArticleClickListener) {
        binding.article = article
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            article?.let {
                clickListener.onArticleClick(it)
            }
        }
    }

    interface OnArticleClickListener {
        fun onArticleClick(article: Article)
    }

    companion object {
        fun create(parent: ViewGroup): ArticleViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NewsSingleItemBinding.inflate(inflater, parent, false)
            return ArticleViewHolder(binding)
        }
    }
}