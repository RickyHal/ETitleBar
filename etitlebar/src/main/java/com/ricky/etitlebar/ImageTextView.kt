package com.ricky.etitlebar

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 *
 * @author Ricky Hal
 * @date 2021/2/4
 */
class ImageTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    private var mTextView: TextView = TextView(context)
    private var mImageView: ImageView = ImageView(context)

    init {
        addView(mTextView)
        addView(mImageView)
        gravity = Gravity.CENTER

        setupTextView()
        setupImageView()
    }

    private fun setupTextView() {
        mTextView.maxLines = 1
        mTextView.setTextColor(Color.BLACK)
        mTextView.ellipsize = TextUtils.TruncateAt.END
        mTextView.gravity = Gravity.CENTER
        showText()
    }

    private fun setupImageView() {
        mImageView.scaleType = ImageView.ScaleType.CENTER
    }

    /**
     * For TextView
     */
    fun setTextColor(@ColorInt color: Int) {
        showText()
        mTextView.setTextColor(color)
    }

    fun setTextGravity(gravity: Int) {
        showText()
        mTextView.gravity = gravity
    }

    fun setText(text: String) {
        showText()
        mTextView.text = text
    }

    fun setText(@StringRes text: Int) {
        showText()
        mTextView.setText(text)
    }

    fun setTextSize(size: Float) {
        showText()
        mTextView.textSize = size
    }

    fun setTextSize(unit: Int, size: Float) {
        showText()
        mTextView.setTextSize(unit, size)
    }

    fun setTypeface(typeface: Typeface) {
        showText()
        mTextView.typeface = typeface
    }

    fun clearText() {
        mTextView = TextView(context)
    }

    /**
     * For ImageView
     */
    fun setScaleType(scaleType: ImageView.ScaleType) {
        showIcon()
        mImageView.scaleType = scaleType
    }

    fun setIcon(@DrawableRes icon: Int) {
        showIcon()
        mImageView.setImageResource(icon)
    }

    fun setIcon(icon: Drawable) {
        showIcon()
        mImageView.setImageDrawable(icon)
    }

    fun clearImage() {
        mImageView = ImageView(context)
    }

    private fun showText() {
        mTextView.visible()
        mImageView.gone()
    }

    private fun showIcon() {
        mTextView.gone()
        mImageView.visible()
    }

    fun clear() {
        mTextView = TextView(context)
        mImageView = ImageView(context)
    }
}