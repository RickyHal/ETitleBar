package com.ricky.etitlebar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.annotation.ColorInt

/**
 *
 * @author Ricky Hal
 * @date 2021/2/3
 */
class ShadowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mIsGradient: Boolean = true
    private var mIsReverse: Boolean = false

    @ColorInt
    private var mShadowColor: Int? = null

    init {
        scaleType = ScaleType.CENTER_CROP
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.ShadowView).apply {
                mIsReverse = getBoolean(R.styleable.ShadowView_reverse, false)
                mShadowColor = getColor(R.styleable.ShadowView_shadow_color, -1)
                recycle()
            }
        }
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    fun withGradient(flag: Boolean) {
        mIsGradient = flag
        invalidate()
    }

    override fun setBackgroundColor(color: Int) {
        mShadowColor = color
        background = null
        invalidate()
    }

    override fun setBackgroundResource(resid: Int) {
        mShadowColor = null
        super.setBackgroundResource(resid)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        mShadowColor?.let {
            val shader = if (mIsGradient) {
                if (mIsReverse)
                    LinearGradient(0f, 0f, 0f, height * 1f, intArrayOf(Color.TRANSPARENT, it), null, Shader.TileMode.REPEAT)
                else
                    LinearGradient(0f, 0f, 0f, height * 1f, intArrayOf(it, Color.TRANSPARENT), null, Shader.TileMode.REPEAT)
            } else null
            mPaint.shader = shader
            mPaint.color = it
            canvas?.drawRect(Rect(0, 0, width, height), mPaint)
        } ?: super.onDraw(canvas)
    }
}