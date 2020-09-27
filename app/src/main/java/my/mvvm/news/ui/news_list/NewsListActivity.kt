package my.mvvm.news.ui.news_list

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import my.mvvm.news.App
import my.mvvm.news.R
import my.mvvm.news.data.model.Article
import my.mvvm.news.databinding.ActivityNewsListBinding
import my.mvvm.news.di.module.ViewModelFactory
import my.mvvm.news.ui.adapter.footer.NewsLoadStateAdapter
import my.mvvm.news.ui.adapter.news.ArticleViewHolder
import my.mvvm.news.ui.adapter.news.NewsAdapter
import my.mvvm.news.ui.base.BaseMVVMActivity
import my.mvvm.news.ui.news_details.NewsDetailsActivity
import my.mvvm.news.utils.AppUtils
import javax.inject.Inject

class NewsListActivity : BaseMVVMActivity<ActivityNewsListBinding, MainViewModel>(),
    ArticleViewHolder.OnArticleClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var mAdapter: NewsAdapter

    private lateinit var mViewModel: MainViewModel
    private lateinit var mBinding: ActivityNewsListBinding
    private var requestNewsJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewDataBinding()

        setUp()
        checkInternetConnection()
        setUpRecyclerView()
        setUpAdapter()
        setUpOnSwipeRefresh()


    }

    private fun setUp() {
        initToolbar(R.id.toolbar, R.string.news)
        setHomeAsUp(false)
    }

    private fun setUpRecyclerView() {
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun setUpAdapter() {
        mBinding.recyclerView.adapter = mAdapter.withLoadStateHeaderAndFooter(
            footer = NewsLoadStateAdapter(),
            header = NewsLoadStateAdapter()
        )

        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow.collectLatest { loadState ->
                mBinding.onSwipeRefresh.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { mBinding.recyclerView.scrollToPosition(0) }
        }

        mAdapter.setOnArticleClickListener(this)
    }

    private fun setUpOnSwipeRefresh() {
        mBinding.onSwipeRefresh.setOnRefreshListener { mAdapter.refresh() }
    }

    private fun fetchNews() {
        requestNewsJob?.cancel()
        requestNewsJob = lifecycleScope.launch {
            mViewModel.getNewsResult().collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun checkInternetConnection() {
        if (AppUtils.hasNetwork(this)) {
            fetchNews()
        } else {
            showToast(R.string.check_internet_connection)
        }
    }

    override fun getViewModel(): MainViewModel {
        mViewModel =
            ViewModelProvider(this, this.viewModelFactory).get(MainViewModel::class.java)
        return mViewModel
    }

    override fun getBindingVariable(): Int? = null

    override fun getLayoutId(): Int = R.layout.activity_news_list

    override fun performDependencyInjection() {
        App.get(this).getAppComponent().inject(this)
    }

    override fun onArticleClick(article: Article) {
        startActivity(NewsDetailsActivity.getStartIntent(this, article))
    }

}