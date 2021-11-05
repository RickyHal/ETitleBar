package com.ricky.etitlebar

import android.content.res.Resources
import android.view.View

/**
 *
 * @author Ricky Hal
 * @date 2021/2/1
 */
fun getStatusBarHeight(): Int {
    val result = 0
    try {
        val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            val sizeOne = Resources.getSystem().getDimensionPixelSize(resourceId)
            val sizeTwo = Resources.getSystem().getDimensionPixelSize(resourceId)
            return if (sizeTwo >= sizeOne) {
                sizeTwo
            } else {
                val densityOne = Resources.getSystem().displayMetrics.density
                val densityTwo = Resources.getSystem().displayMetrics.density
                val f = sizeOne * densityTwo / densityOne
                (if (f >= 0) f + 0.5f else f - 0.5f).toInt()
            }
        }
    } catch (ignored: Resources.NotFoundException) {
        return 0
    }
    return result
}

internal fun View.gone() {
    visibility = View.GONE
}

internal fun View.visible() {
    visibility = View.VISIBLE
}

internal fun View.visually(flag: Boolean) {
    if (flag) visible() else gone()
}

internal val Int.dp
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
