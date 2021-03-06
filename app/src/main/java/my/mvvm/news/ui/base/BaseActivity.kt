package my.mvvm.news.ui.base

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var mToast: Toast? = null

    // Toast
    fun showToast(@StringRes message: Int) {
        showToast(getString(message))
    }

    fun showToast(message: String) {
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        mToast!!.show()
    }

    // ActionBar
    fun setActionBarTitle(title: String) {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = title
    }

    fun setActionBarTitle(@StringRes title: Int) {
        setActionBarTitle(getString(title))
    }

    fun showActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.show()
    }

    fun hideActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }

    // Toolbar
    private fun initToolbar(toolbar: Toolbar, title: String?) {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title?.let { supportActionBar?.setTitle(title) }
    }

    fun initToolbar(@IdRes toolbarId: Int, @StringRes title: Int) {
        initToolbar(findViewById<Toolbar>(toolbarId), getString(title))
    }

    fun initToolbar(@IdRes toolbarId: Int, title: String?) {
        initToolbar(findViewById<Toolbar>(toolbarId), title)
    }


    fun setHomeAsUp(status: Boolean) {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(status)
    }
}