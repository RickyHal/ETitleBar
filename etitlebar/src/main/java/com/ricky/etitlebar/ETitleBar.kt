@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.ricky.etitlebar

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams

/**
 *
 * @author Ricky Hal
 * @date 2021/1/27
 *
 * ————————————————————————————————————————————————
 * | Left      |     Center           | Right      |
 * |           |                      |            |
 * +++++++++++++++++++++++++++++++++++++++++++++++++   <- Shadow
 *
 */
class ETitleBar private constructor(context: Context) : RelativeLayout(context) {
    companion object {
        internal val TITLE_BAR_HEIGHT = 48.dp
        internal val SHADOW_HEIGHT = 5.dp

        internal fun createLayout(
            context: Context,
            width: Int = ConstraintLayout.LayoutParams.WRAP_CONTENT,
            height: Int = ConstraintLayout.LayoutParams.MATCH_PARENT
        ): LinearLayout {
            val layout = LinearLayout(context)
            layout.id = View.generateViewId()
            layout.layoutParams = ConstraintLayout.LayoutParams(width, height)
            layout.orientation = LinearLayout.HORIZONTAL
            layout.setHorizontalGravity(Gravity.CENTER)
            return layout
        }

        internal fun createButton(
            context: Context,
            width: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
            height: Int = LinearLayout.LayoutParams.MATCH_PARENT
        ): ImageTextView {
            val imageTextView = ImageTextView(context)
            imageTextView.layoutParams = LinearLayout.LayoutParams(width, height)
            return imageTextView
        }

        internal fun createShadowView(
            context: Context,
            width: Int = LinearLayout.LayoutParams.MATCH_PARENT,
            height: Int = SHADOW_HEIGHT
        ): ShadowView {
            val imageView = ShadowView(context)
            imageView.layoutParams = LinearLayout.LayoutParams(width, height)
            return imageView
        }
    }

    private val titleLayout: ConstraintLayout by lazy {
        ConstraintLayout(context).apply {
            id = R.id.etitle_bar_layout
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
    }
    private val backgroundView: ImageView by lazy {
        ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
    }
    private lateinit var builder: Builder
    private var isSetup = false
    private val contentLayoutParam = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

    constructor(builder: Builder) : this(builder.context) {
        this.builder = builder
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val titleLayoutLp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        titleLayoutLp.addRule(ALIGN_PARENT_BOTTOM)
        addView(backgroundView)
        addView(titleLayout, titleLayoutLp)
        setup(builder)
    }

    override fun getId(): Int {
        return R.id.etitle_bar
    }

    private fun setup(builder: Builder) {
        builder.run {
            visually(showTitleBar)
            titleBarBackground?.let {
                backgroundView.setImageResource(it)
            } ?: run {
                backgroundView.setImageResource(0)
                backgroundView.background = null
                backgroundView.setBackgroundColor(titleBarBackgroundColor)
            }
            alpha = titleBarAlpha
            shadowView.alpha = titleBarAlpha
            layoutParams.height = if (fitSystemWindows) titleBarHeight + getStatusBarHeight() else titleBarHeight
            leftLayout.minimumWidth = titleBarHeight
            rightLayout.minimumWidth = titleBarHeight
            titleLayout.layoutParams.height = titleBarHeight
            titleLayout.updateLayoutParams<MarginLayoutParams> {
                setMargins(0, if (fitSystemWindows) getStatusBarHeight() else 0, 0, 0)
            }
            contentLayoutParam.apply {
                setMargins(0, if (!builder.overlapTitleBar && showTitleBar) layoutParams.height else 0, 0, 0)
            }
            centerLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
                goneStartMargin = titleBarHeight
                goneEndMargin = titleBarHeight
            }

            if (!isSetup) {
                titleLayout.removeAllViews()
                titleLayout.addView(leftLayout)
                titleLayout.addView(centerLayout)
                titleLayout.addView(rightLayout)
                isSetup = true
            }

            leftLayout.children.forEachIndexed { index, view ->
                view.setOnClickListener {
                    clickListener?.onLeftClick(index, view, leftLayout)
                }
            }
            centerLayout.children.forEachIndexed { index, view ->
                view.setOnClickListener {
                    clickListener?.onCenterClick(index, view, centerLayout)
                }
            }
            rightLayout.children.forEachIndexed { index, view ->
                view.setOnClickListener {
                    clickListener?.onRightClick(index, view, rightLayout)
                }
            }
        }
    }

    internal fun init(contentView: View): View {
        val shadow = if (builder.shadowView.isVisible) builder.shadowView else null
        val layout = RelativeLayout(builder.context)
        layout.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        // ContentView
        contentLayoutParam.apply {
            if (!builder.overlapTitleBar) setMargins(0, layoutParams.height, 0, 0)
        }
        layout.addView(contentView, contentLayoutParam)

        // TitleBar
        layout.addView(this)

        // Shadow
        shadow?.let {
            val shadowLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, it.layoutParams.height)
            shadowLayoutParams.addRule(BELOW, id)
            layout.addView(it, shadowLayoutParams)
        }
        return layout
    }

    /**
     * 更新导航栏
     * @param block Builder DSL
     */
    fun update(block: Builder.() -> Unit) {
        builder.apply(block)
        setup(builder)
    }

    class Builder(internal val context: Context) {
        internal var titleBarHeight = TITLE_BAR_HEIGHT

        @FloatRange(from = 0.0, to = 1.0)
        internal var titleBarAlpha: Float = 1f

        @DrawableRes
        internal var titleBarBackground: Int? = null

        @ColorInt
        internal var titleBarBackgroundColor: Int = Color.WHITE
        internal var fitSystemWindows: Boolean = true
        internal var overlapTitleBar: Boolean = false
        internal var showTitleBar: Boolean = true
        internal var clickListener: OnTitleBarClickListener? = null

        internal var leftButton: ImageTextView = createButton(context)                                                 // 左边按钮
        internal var titleView: ImageTextView = createButton(context, LayoutParams.WRAP_CONTENT)                       // 标题
        internal var rightButton: ImageTextView = createButton(context)                                                // 右边按钮
        internal var shadowView: ShadowView = createShadowView(context, SHADOW_HEIGHT)                                 // 阴影
        internal var leftLayout: LinearLayout = createLayout(context)                                             // 左边layout
        internal var centerLayout: LinearLayout = createLayout(context)                                           // 中间layout
        internal var rightLayout: LinearLayout = createLayout(context)                                            // 右边layout

        init {
            leftLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToStart = R.id.etitle_bar_layout
            }
            centerLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
                width = 0
                startToEnd = leftLayout.id
                endToStart = rightLayout.id
            }
            rightLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
                endToEnd = R.id.etitle_bar_layout
            }
            leftLayout.setHorizontalGravity(Gravity.START or Gravity.BOTTOM)
            centerLayout.setHorizontalGravity(Gravity.CENTER)
            rightLayout.setHorizontalGravity(Gravity.END or Gravity.BOTTOM)
            setTitleTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            setShadowColor(Color.parseColor("#33000000"))
            setTitleBarBackgroundColor(Color.WHITE)
        }

        /** ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 导航栏左侧内容 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

        /**
         * 设置左侧按钮文字，右侧->[setRightButtonText]
         * @param text 文字内容
         */
        fun setLeftButtonText(text: String) = apply {
            leftButton.setText(text)
        }

        /**
         * 设置左侧按钮文字，右侧->[setRightButtonText]
         * @param text 文字id
         */
        fun setLeftButtonText(@StringRes text: Int) = apply {
            leftButton.setText(text)
        }

        /**
         * 设置左侧按钮文字字体大小，右侧->[setRightButtonTextSize]
         * @param size 字体大小
         */
        fun setLeftButtonTextSize(size: Float) = apply {
            leftButton.setTextSize(size)
        }

        /**
         * 设置左侧按钮文字字体大小，右侧->[setRightButtonTextSize]
         * @param unit 字体单位[TypedValue]
         * @param size 字体大小
         */
        fun setLeftButtonTextSize(unit: Int, size: Float) = apply {
            leftButton.setTextSize(unit, size)
        }

        /**
         * 设置左侧按钮图标，右侧->[setRightButtonIcon]
         * @param icon 图标id
         */
        fun setLeftButtonIcon(@DrawableRes icon: Int) = apply {
            leftButton.setIcon(icon)
        }

        /**
         * 设置左侧按钮图标，右侧->[setRightButtonIcon]
         * @param icon 图标drawable
         */
        fun setLeftButtonIcon(icon: Drawable) = apply {
            leftButton.setIcon(icon)
        }

        /**
         * 设置左侧按钮文字颜色，右侧->[setRightButtonTextColor]
         * @param color 颜色id
         */
        fun setLeftButtonTextColor(@ColorInt color: Int) = apply {
            leftButton.setTextColor(color)
        }

        /**
         * 设置左侧按钮文字字体，右侧->[setRightButtonTextColor]
         * @param typeface 字体[Typeface]
         */
        fun setLeftButtonTypeface(typeface: Typeface) = apply {
            leftButton.setTypeface(typeface)
        }

        /**
         * 设置左侧按钮Padding，右侧->[setRightButtonPadding]
         * @param left
         * @param top
         * @param right
         * @param bottom
         */
        fun setLeftButtonPadding(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) = apply {
            leftButton.setPadding(left, top, right, bottom)
        }

        /**
         * 设置左侧按钮Margin
         * @param left
         * @param top
         * @param right
         * @param bottom
         */
        fun setLeftButtonMargin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) = apply {
            leftButton.updateLayoutParams<MarginLayoutParams> {
                setMargins(left, top, right, bottom)
            }
        }

        /**
         * 修改左侧按钮属性
         */
        fun applyLeftButton(block: (ImageTextView) -> Unit) = apply {
            block(leftButton)
        }

        /**
         * 修改左边区域属性
         * @param block
         */
        fun applyLeftLayout(block: (LinearLayout) -> Unit) = apply {
            block(leftLayout)
        }

        /**
         * 设置自定义的Left View
         */
        fun setLeftView(view: View) = apply {
            leftLayout.removeAllViews()
            leftLayout.addView(view)
        }

        /**
         * 重置左侧区域，会清空左侧区域内的自定义按钮，并添加默认的按钮
         */
        fun resetLeftView() = apply {
            leftLayout.removeAllViews()
            leftLayout.addView(leftButton)
        }

        /**
         * 添加多个Left View
         */
        fun addLeftView(view: View) = apply {
            leftLayout.addView(view)
        }

        /**
         * 设置左侧区域水平对齐
         * @param gravity
         */
        fun setLeftGravity(gravity: Int) = apply {
            leftLayout.setHorizontalGravity(gravity)
        }

        /** ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 导航栏中间内容 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        /**
         * 设置导航栏标题
         * @param text 标题
         */
        fun setTitle(text: String) = apply {
            titleView.setText(text)
        }

        /**
         * 设置导航栏标题
         * @param text 标题id
         */
        fun setTitle(@StringRes text: Int) = apply {
            titleView.setText(text)
        }

        /**
         * 设置导航栏标题字体颜色
         * @param color 字体颜色
         */
        fun setTitleColor(@ColorInt color: Int) = apply {
            titleView.setTextColor(color)
        }

        /**
         * 设置导航栏标题字体大小
         * @param size 字体大小
         */
        fun setTitleTextSize(size: Float) = apply {
            titleView.setTextSize(size)
        }

        /**
         * 设置导航栏标题字体大小
         * @param unit 字体单位[TypedValue]
         * @param size 字体大小
         */
        fun setTitleTextSize(unit: Int, size: Float) = apply {
            titleView.setTextSize(unit, size)
        }

        /**
         * 设置标题字体样式
         * @param typeface 样式[Typeface]
         */
        fun setTitleTypeface(typeface: Typeface) = apply {
            titleView.setTypeface(typeface)
        }

        /**
         * 设置导航栏标题padding
         */
        fun setTitleViewPadding(left: Int, top: Int, right: Int, bottom: Int) = apply {
            titleView.setPadding(left, top, right, bottom)
        }

        /**
         * 设置标题View Margin
         * @param left
         * @param top
         * @param right
         * @param bottom
         */
        fun setTitleViewMargin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) = apply {
            titleView.updateLayoutParams<MarginLayoutParams> {
                setMargins(left, top, right, bottom)
            }
        }

        /**
         * 修改标题View属性
         * @param block
         */
        fun applyTitleView(block: (ImageTextView) -> Unit) = apply {
            block(titleView)
        }

        /**
         * 修改中间区域属性
         * @param block
         */
        fun applyCenterLayout(block: (LinearLayout) -> Unit) = apply {
            block(centerLayout)
        }

        /**
         * 设置自定义的Center View
         * @param view
         */
        fun setCenterView(view: View) = apply {
            centerLayout.removeAllViews()
            centerLayout.addView(view)
        }

        /**
         * 重置中间区域，会清空中间区域内的自定义按钮，并添加默认的按钮
         */
        fun resetCenterView() = apply {
            centerLayout.removeAllViews()
            centerLayout.addView(titleView)
        }

        /**
         * 添加多个自定义的Center View
         * @param view
         */
        fun addCenterView(view: View) = apply {
            centerLayout.addView(view)
        }

        /**
         * 设置中间区域水平对齐
         * @param gravity
         */
        fun setCenterGravity(gravity: Int) = apply {
            centerLayout.setHorizontalGravity(gravity)
        }
        /** ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 导航栏右侧内容 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

        /**
         * 设置右侧按钮文字，左侧->[setLeftButtonText]
         * @param text 文字内容
         */
        fun setRightButtonText(text: String) = apply {
            rightButton.setText(text)
        }

        /**
         * 设置右侧按钮文字，左侧->[setLeftButtonText]
         * @param text 文字id
         */
        fun setRightButtonText(@StringRes text: Int) = apply {
            rightButton.setText(text)
        }

        /**
         * 设置右侧按钮文字字体大小，左侧->[setLeftButtonTextSize]
         * @param size 字体大小
         */
        fun setRightButtonTextSize(size: Float) = apply {
            rightButton.setTextSize(size)
        }

        /**
         * 设置右侧按钮文字字体大小，左侧->[setLeftButtonTextSize]
         * @param unit 字体单位[TypedValue]
         * @param size 字体大小
         */
        fun setRightButtonTextSize(unit: Int, size: Float) = apply {
            rightButton.setTextSize(unit, size)
        }

        /**
         * 设置右侧按钮图标，左侧->[setLeftButtonIcon]
         * @param icon 图标id
         */
        fun setRightButtonIcon(@DrawableRes icon: Int) = apply {
            rightButton.setIcon(icon)
        }

        /**
         * 设置右侧按钮图标，左侧->[setLeftButtonIcon]
         * @param icon 图标drawable
         */
        fun setRightButtonIcon(icon: Drawable) = apply {
            rightButton.setIcon(icon)
        }

        /**
         * 设置右侧按钮文字颜色，右侧->[setLeftButtonTextColor]
         * @param color 颜色id
         */
        fun setRightButtonTextColor(@ColorInt color: Int) = apply {
            rightButton.setTextColor(color)
        }

        /**
         * 设置右侧按钮文字字体，右侧->[setLeftButtonTypeface]
         * @param typeface 字体[Typeface]
         */
        fun setRightButtonTypeface(typeface: Typeface) = apply {
            rightButton.setTypeface(typeface)
        }

        /**
         * 设置右侧按钮Padding，左侧->[setLeftButtonPadding]
         */
        fun setRightButtonPadding(left: Int, top: Int, right: Int, bottom: Int) = apply {
            rightButton.setPadding(left, top, right, bottom)
        }

        /**
         * 设置右边按钮 Margin
         * @param left
         * @param top
         * @param right
         * @param bottom
         */
        fun setRightButtonMargin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) = apply {
            rightButton.updateLayoutParams<MarginLayoutParams> {
                setMargins(left, top, right, bottom)
            }
        }

        /**
         * 修改右侧按钮属性
         * @param block
         */
        fun applyRightButton(block: (ImageTextView) -> Unit) = apply {
            block(rightButton)
        }

        /**
         * 修改右侧区域属性
         * @param block
         */
        fun applyRightLayout(block: (LinearLayout) -> Unit) = apply {
            block(rightLayout)
        }

        /**
         * 设置自定义的右边按钮
         * @param view
         */
        fun setRightView(view: View) = apply {
            rightLayout.removeAllViews()
            rightLayout.addView(view)
        }

        /**
         * 重置右侧区域，会清空右侧区域内的自定义按钮，并添加默认的按钮
         */
        fun resetRightView() = apply {
            rightLayout.removeAllViews()
            rightLayout.addView(rightButton)
        }

        /**
         * 添加自定义的右边按钮
         * @param view
         */
        fun addRightView(view: View) = apply {
            rightLayout.addView(view)
        }

        /**
         * 设置中间区域水平对齐
         * @param gravity
         */
        fun setRightGravity(gravity: Int) = apply {
            rightLayout.setHorizontalGravity(gravity)
        }
        /** ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 导航栏阴影设置 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

        /**
         * 设置导航栏阴影颜色
         * @param color 颜色id
         */
        fun setShadowColor(@ColorInt color: Int) = apply {
            shadowView.setBackgroundColor(color)
        }

        /**
         * 设置导航栏阴影图片
         * @param drawableId 阴影图片id
         */
        fun setShadowDrawable(@DrawableRes drawableId: Int) = apply {
            shadowView.setBackgroundResource(drawableId)
        }

        /**
         * 设置导航栏阴影图片
         * @param drawable 阴影图片
         */
        fun setShadowDrawable(drawable: Drawable) = apply {
            shadowView.background = drawable
        }

        /**
         * 设置导航栏阴影是否渐变，从上到下会有一个渐变的效果
         * @param flag 是否渐变
         */
        fun withGradient(flag: Boolean) = apply {
            shadowView.withGradient(flag)
        }

        /**
         * 设置导航栏阴影高度
         * @param height 阴影高度
         */
        fun setShadowHeight(height: Int) = apply {
            shadowView.layoutParams.height = height
        }

        /**
         * 设置是否有导航栏阴影
         * @param flag
         */
        fun withShadow(flag: Boolean) = apply {
            shadowView.visually(flag)
        }

        /** ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 导航栏背景设置 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

        /**
         * 设置导航栏背景颜色
         * @param colorId 颜色id
         */
        fun setTitleBarBackgroundColor(@ColorInt colorId: Int) = apply {
            titleBarBackgroundColor = colorId
            titleBarBackground = null
        }

        /**
         * 设置导航栏背景
         * @param drawableId 背景
         */
        fun setTitleBarBackground(@DrawableRes drawableId: Int) = apply {
            titleBarBackground = drawableId
            titleBarBackgroundColor = Color.WHITE
        }

        /**
         * 设置左侧区域背景颜色
         * @param colorId 颜色Id
         */
        fun setLeftLayoutBackgroundColor(@ColorInt colorId: Int) = apply {
            leftLayout.setBackgroundColor(colorId)
        }

        /**
         * 设置左侧区域背景
         * @param drawableId 背景
         */
        fun setLeftLayoutBackground(@DrawableRes drawableId: Int) = apply {
            leftLayout.setBackgroundResource(drawableId)
        }

        /**
         * 设置中间区域背景颜色
         * @param colorId 颜色id
         */
        fun setCenterLayoutBackgroundColor(@ColorInt colorId: Int) = apply {
            centerLayout.setBackgroundColor(colorId)
        }

        /**
         * 设置中间区域背景
         * @param drawableId 背景
         */
        fun setCenterLayoutBackground(@DrawableRes drawableId: Int) = apply {
            centerLayout.setBackgroundResource(drawableId)
        }

        /**
         * 设置右侧区域背景颜色
         * @param colorId 颜色id
         */
        fun setRightLayoutBackgroundColor(@ColorInt colorId: Int) = apply {
            rightLayout.setBackgroundColor(colorId)
        }

        /**
         * 设置右侧区域背景
         * @param drawableId 背景
         */
        fun setRightLayoutBackground(@DrawableRes drawableId: Int) = apply {
            rightLayout.setBackgroundResource(drawableId)
        }

        /**
         * 设置导航栏透明度，注意透明度为0时会隐藏导航栏
         * @param alpha 透明度
         */
        fun setTitleBarAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) = apply {
            titleBarAlpha = alpha
        }

        /** ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 通用设置 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

        /**
         * 在导航栏顶部加一个状态栏高度的padding
         * @param fitSystemWindows 是否匹配状态栏
         */
        fun fitsSystemWindows(fitSystemWindows: Boolean) = apply {
            this.fitSystemWindows = fitSystemWindows
        }

        /**
         * 导航栏和ContentView是否重叠，沉浸式导航栏可能需要
         * @param overlapTitleBar 是否重叠，默认否
         */
        fun overlapTitleBar(overlapTitleBar: Boolean) = apply {
            this.overlapTitleBar = overlapTitleBar
        }

        /**
         * 设置导航栏高度
         * @param height 导航栏高度
         */
        fun setTitleBarHeight(height: Int) = apply {
            titleBarHeight = height
            leftLayout.layoutParams.height = titleBarHeight
            centerLayout.layoutParams.height = titleBarHeight
            rightLayout.layoutParams.height = titleBarHeight
        }

        /**
         * 是否显示左侧按钮
         * @param hasLeftButton
         */
        fun hasLeftButton(hasLeftButton: Boolean) = apply {
            leftLayout.visually(hasLeftButton)
        }

        /**
         * 是否显示标题
         * @param hasTitle
         */
        fun hasCenter(hasTitle: Boolean) = apply {
            centerLayout.visually(hasTitle)
        }

        /**
         * 显示导航栏
         */
        fun show() = apply {
            showTitleBar = true
        }

        /**
         * 隐藏导航栏
         */
        fun hide() = apply {
            showTitleBar = false
        }

        /**
         * 是否显示右侧按钮
         * @param hasRightButton
         */
        fun hasRightButton(hasRightButton: Boolean) = apply {
            rightLayout.visually(hasRightButton)
        }

        /**
         * 设置点击监听
         * @param listener
         */
        fun setOnClickListener(listener: OnTitleBarClickListener) = apply {
            clickListener = listener
        }

        /**
         * 构建导航栏
         */
        fun build(): ETitleBar {
            leftLayout.addView(leftButton)
            centerLayout.addView(titleView)
            rightLayout.addView(rightButton)
            return ETitleBar(this)
        }
    }
}