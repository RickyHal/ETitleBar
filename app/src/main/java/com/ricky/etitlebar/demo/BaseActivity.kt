package com.ricky.etitlebar.demo

import android.content.res.Resources
import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.ricky.etitlebar.ETitleBar
import com.ricky.etitlebar.ETitleBarActivity

/**
 *
 * @author Ricky Hal
 * @date 2021/11/4
 */
abstract class BaseActivity : ETitleBarActivity() {
    protected val Int.dp
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    override fun createTitleBar(): ETitleBar.Builder? {
        return ETitleBar.Builder(this)
            .setTitle("ETitleBar Demo")
            .fitsSystemWindows(true)
            .setLeftButtonPadding(left = 15.dp, right = 15.dp)
            .setLeftButtonText("Back")
            .withShadow(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this)
            .transparentBar()
            .statusBarDarkFont(true)
            .init()
    }
}