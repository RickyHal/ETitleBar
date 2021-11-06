package com.ricky.etitlebar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author Ricky Hal
 * @date 2021/11/4
 */
abstract class ETitleBarActivity : AppCompatActivity(), OnTitleBarClickListener {
    private lateinit var titleBar: ETitleBar
    private var isInitedTitleBar = false

    private fun initTitleBar(view: View): View {
        val builder = createTitleBar() ?: return view
        titleBar = builder.setOnClickListener(this).build()
        return titleBar.init(view).also { isInitedTitleBar = true }
    }

    protected abstract fun createTitleBar(): ETitleBar.Builder?

    override fun setContentView(view: View?) {
        view ?: return
        val wrapperView = initTitleBar(view)
        super.setContentView(wrapperView)
    }

    override fun setContentView(layoutResID: Int) {
        val view = LayoutInflater.from(this).inflate(layoutResID, null, false)
        setContentView(view)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        view ?: return
        val wrapperView = initTitleBar(view)
        super.setContentView(wrapperView, params)
    }

    override fun setTitle(title: CharSequence?) {
        if (isInitedTitleBar && ::titleBar.isInitialized) {
            titleBar.update {
                setTitle(title.toString())
            }
        }
        super.setTitle(title)
    }

    protected fun updateTitleBar(block: ETitleBar.Builder.() -> Unit) {
        if (isInitedTitleBar && ::titleBar.isInitialized) {
            titleBar.update(block)
        }
    }
}