package com.ricky.etitlebar

import android.view.View
import android.widget.LinearLayout

/**
 *
 * @author Ricky Hal
 * @date 2021/2/1
 */
interface OnTitleBarClickListener {
    fun onLeftClick(index: Int, view: View, layout: LinearLayout) {}
    fun onCenterClick(index: Int, view: View, layout: LinearLayout) {}
    fun onRightClick(index: Int, view: View, layout: LinearLayout) {}
}