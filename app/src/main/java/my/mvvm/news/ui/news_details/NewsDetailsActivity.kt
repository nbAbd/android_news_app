package my.mvvm.news.ui.news_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import my.mvvm.news.R
import my.mvvm.news.data.model.Article
import my.mvvm.news.databinding.ActivityNewsDetailsBinding
import my.mvvm.news.ui.base.BaseActivity

class NewsDetailsActivity : BaseActivity() {
    companion object {
        fun getStartIntent(context: Context, article: Article): Intent {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra("article", article)
            return intent
        }
    }

    private lateinit var mBinding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        setUp()

        val article = intent.getParcelableExtra<Article>("article")
        article?.let {
            mBinding.article = it
        }
    }

    private fun setUp() {
        initToolbar(R.id.toolbar, R.string.news)
        setHomeAsUp(true)
    }
}