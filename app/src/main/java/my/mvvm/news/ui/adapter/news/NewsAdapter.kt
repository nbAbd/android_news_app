package my.mvvm.news.ui.adapter.news

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import my.mvvm.news.data.model.Article

class NewsAdapter : PagingDataAdapter<Article, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    private var articleClickListener: ArticleViewHolder.OnArticleClickListener? = null

    fun setOnArticleClickListener(clickListener: ArticleViewHolder.OnArticleClickListener) {
        this.articleClickListener = clickListener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            articleClickListener?.let { (holder as ArticleViewHolder).bind(article, it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder.create(parent)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.title == newItem.title


            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}