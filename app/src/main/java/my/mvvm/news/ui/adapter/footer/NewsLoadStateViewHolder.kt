package my.mvvm.news.ui.adapter.footer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import my.mvvm.news.R
import my.mvvm.news.databinding.LoadstateFooterItemBinding

class NewsLoadStateViewHolder(
    private val binding: LoadstateFooterItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup): NewsLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loadstate_footer_item, parent, false)
            val binding = LoadstateFooterItemBinding.bind(view)
            return NewsLoadStateViewHolder(binding)
        }
    }
}