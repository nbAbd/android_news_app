package my.mvvm.news.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseMVVMActivity<T : ViewDataBinding, VM : ViewModel> : BaseActivity() {

    private lateinit var mViewDataBinding: T
    private var mViewModel: VM? = null

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        performDataBinding()
    }

    /**
     * Override for set viewModel
     *
     *  @return viewModel instance
     */
    abstract fun getViewModel(): VM

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int?

    /***
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    private fun performDataBinding() {
        this.mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        getBindingVariable()?.let { variableId ->
            mViewDataBinding.setVariable(
                variableId,
                mViewModel
            )
        }
        mViewDataBinding.executePendingBindings()
    }

    abstract fun performDependencyInjection()


}