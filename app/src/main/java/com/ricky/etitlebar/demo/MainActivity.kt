package com.ricky.etitlebar.demo

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.ricky.etitlebar.demo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val colors = mutableListOf<Int>(Color.BLACK, Color.RED, Color.GRAY, Color.GREEN, Color.CYAN, Color.YELLOW)
    private val bgs = mutableListOf<Int>(Color.WHITE, Color.RED, Color.GRAY, Color.GREEN, Color.CYAN, Color.YELLOW)
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var isLeftBold = false
    private var isTitleBold = false
    private var isRightBold = false
    private var shadowGradient = true
    private var showShadow = true
    private var showTitleBar = true
    private var isFitSystemWindows = true
    private var isOverlapTitleBar = false
    private var showLeft = true
    private var showCenter = true
    private var showRight = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initLeft()
        initCenter()
        initRight()
        initShadow()
        initBackground()
        initCommon()
    }

    private fun initLeft() {
        binding.etLeft.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setLeftButtonText(it.toString())
                }
            }
        }
        binding.etLeftTextSize.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setLeftButtonTextSize(12f.coerceAtLeast(it.toString().toFloat()))
                }
            }
        }
        binding.btnLeftIcon.setOnClickListener {
            updateTitleBar {
                setLeftButtonIcon(R.drawable.icon_back)
            }
        }
        binding.btnLeftTextColor.setOnClickListener {
            updateTitleBar {
                setLeftButtonTextColor(colors.random())
            }
        }
        binding.btnLeftTextTypeface.setOnClickListener {
            updateTitleBar {
                if (!isLeftBold) {
                    setLeftButtonTypeface(Typeface.DEFAULT_BOLD)
                } else {
                    setLeftButtonTypeface(Typeface.DEFAULT)
                }
                isLeftBold = !isLeftBold
            }
        }
        binding.etLeftPaddingLeft.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftPadding()
            }
        }
        binding.etLeftPaddingTop.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftPadding()
            }
        }
        binding.etLeftPaddingRight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftPadding()
            }
        }
        binding.etLeftPaddingBottom.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftPadding()
            }
        }
        binding.etLeftMarginLeft.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftMargin()
            }
        }
        binding.etLeftMarginTop.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftMargin()
            }
        }
        binding.etLeftMarginRight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftMargin()
            }
        }
        binding.etLeftMarginBottom.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateLeftMargin()
            }
        }
        binding.btnApplyLeftBtn.setOnClickListener {
            updateTitleBar {
                applyLeftButton {
                    it.setText("Cancel")
                }
            }
        }
        binding.btnApplyLeftLayout.setOnClickListener {
            updateTitleBar {
                applyLeftLayout {
                    it.setHorizontalGravity(Gravity.END)
                }
            }
        }
        binding.btnSetLeftView.setOnClickListener {
            updateTitleBar {
                setLeftView(createTextView())
            }
        }
        binding.btnResetLeftView.setOnClickListener {
            updateTitleBar {
                resetLeftView()
            }
        }
        binding.btnAddLeftView.setOnClickListener {
            updateTitleBar {
                addLeftView(createTextView())
            }
        }
        binding.rgLeftGravity.setOnCheckedChangeListener { _, checkedId ->
            val gravity = when (checkedId) {
                R.id.rg_left_gravity_start -> Gravity.START
                R.id.rg_left_gravity_center -> Gravity.CENTER
                R.id.rg_left_gravity_right -> Gravity.END
                else -> Gravity.CENTER
            }
            updateTitleBar {
                setLeftGravity(gravity)
            }
        }
    }

    private fun updateLeftPadding() {
        val leftText = binding.etLeftPaddingLeft.text
        val left = if (leftText.isNullOrEmpty()) 0 else leftText.toString().toInt()
        val topText = binding.etLeftPaddingTop.text
        val top = if (topText.isNullOrEmpty()) 0 else topText.toString().toInt()
        val rightText = binding.etLeftPaddingRight.text
        val right = if (rightText.isNullOrEmpty()) 0 else rightText.toString().toInt()
        val bottomText = binding.etLeftPaddingBottom.text
        val bottom = if (bottomText.isNullOrEmpty()) 0 else bottomText.toString().toInt()
        updateTitleBar {
            setLeftButtonPadding(left, top, right, bottom)
        }
    }

    private fun updateLeftMargin() {
        val leftText = binding.etLeftMarginLeft.text
        val left = if (leftText.isNullOrEmpty()) 0 else leftText.toString().toInt()
        val topText = binding.etLeftMarginTop.text
        val top = if (topText.isNullOrEmpty()) 0 else topText.toString().toInt()
        val rightText = binding.etLeftMarginRight.text
        val right = if (rightText.isNullOrEmpty()) 0 else rightText.toString().toInt()
        val bottomText = binding.etLeftMarginBottom.text
        val bottom = if (bottomText.isNullOrEmpty()) 0 else bottomText.toString().toInt()
        updateTitleBar {
            setLeftButtonMargin(left, top, right, bottom)
        }
    }

    private fun updateTitlePadding() {
        val leftText = binding.etTitlePaddingLeft.text
        val left = if (leftText.isNullOrEmpty()) 0 else leftText.toString().toInt()
        val topText = binding.etTitlePaddingTop.text
        val top = if (topText.isNullOrEmpty()) 0 else topText.toString().toInt()
        val rightText = binding.etTitlePaddingRight.text
        val right = if (rightText.isNullOrEmpty()) 0 else rightText.toString().toInt()
        val bottomText = binding.etTitlePaddingBottom.text
        val bottom = if (bottomText.isNullOrEmpty()) 0 else bottomText.toString().toInt()
        updateTitleBar {
            setTitleViewPadding(left, top, right, bottom)
        }
    }

    private fun updateTitleMargin() {
        val leftText = binding.etTitleMarginLeft.text
        val left = if (leftText.isNullOrEmpty()) 0 else leftText.toString().toInt()
        val topText = binding.etTitleMarginTop.text
        val top = if (topText.isNullOrEmpty()) 0 else topText.toString().toInt()
        val rightText = binding.etTitleMarginRight.text
        val right = if (rightText.isNullOrEmpty()) 0 else rightText.toString().toInt()
        val bottomText = binding.etTitleMarginBottom.text
        val bottom = if (bottomText.isNullOrEmpty()) 0 else bottomText.toString().toInt()
        updateTitleBar {
            setTitleViewMargin(left, top, right, bottom)
        }
    }

    private fun updateRightPadding() {
        val leftText = binding.etRightPaddingLeft.text
        val left = if (leftText.isNullOrEmpty()) 0 else leftText.toString().toInt()
        val topText = binding.etRightPaddingTop.text
        val top = if (topText.isNullOrEmpty()) 0 else topText.toString().toInt()
        val rightText = binding.etRightPaddingRight.text
        val right = if (rightText.isNullOrEmpty()) 0 else rightText.toString().toInt()
        val bottomText = binding.etTitlePaddingBottom.text
        val bottom = if (bottomText.isNullOrEmpty()) 0 else bottomText.toString().toInt()
        updateTitleBar {
            setRightButtonPadding(left, top, right, bottom)
        }
    }

    private fun updateRightMargin() {
        val leftText = binding.etRightMarginLeft.text
        val left = if (leftText.isNullOrEmpty()) 0 else leftText.toString().toInt()
        val topText = binding.etRightMarginTop.text
        val top = if (topText.isNullOrEmpty()) 0 else topText.toString().toInt()
        val rightText = binding.etRightMarginRight.text
        val right = if (rightText.isNullOrEmpty()) 0 else rightText.toString().toInt()
        val bottomText = binding.etRightMarginBottom.text
        val bottom = if (bottomText.isNullOrEmpty()) 0 else bottomText.toString().toInt()
        updateTitleBar {
            setRightButtonMargin(left, top, right, bottom)
        }
    }


    private fun initCenter() {
        binding.etTitle.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setTitle(it.toString())
                }
            }
        }
        binding.btnTitleColor.setOnClickListener {
            updateTitleBar { setTitleColor(colors.random()) }
        }
        binding.etCenterTextSize.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setTitleTextSize(12f.coerceAtLeast(it.toString().toFloat()))
                }
            }
        }
        binding.btnTitleTextTypeface.setOnClickListener {
            updateTitleBar {
                if (!isTitleBold) {
                    setTitleTypeface(Typeface.DEFAULT_BOLD)
                } else {
                    setTitleTypeface(Typeface.DEFAULT)
                }
                isTitleBold = !isTitleBold
            }
        }
        binding.etTitlePaddingLeft.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitlePadding()
            }
        }
        binding.etTitlePaddingTop.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitlePadding()
            }
        }
        binding.etTitlePaddingRight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitlePadding()
            }
        }
        binding.etTitlePaddingBottom.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitlePadding()
            }
        }
        binding.etTitleMarginLeft.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleMargin()
            }
        }
        binding.etTitleMarginTop.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleMargin()
            }
        }
        binding.etTitleMarginRight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleMargin()
            }
        }
        binding.etTitleMarginBottom.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleMargin()
            }
        }
        binding.btnApplyTitle.setOnClickListener {
            updateTitleBar {
                applyTitleView {
                    it.setText("Hello world")
                }
            }
        }
        binding.btnApplyCenterLayout.setOnClickListener {
            updateTitleBar {
                applyCenterLayout {
                    it.setHorizontalGravity(Gravity.START)
                }
            }
        }
        binding.btnSetCenterView.setOnClickListener {
            updateTitleBar {
                setCenterView(createTextView())
            }
        }
        binding.btnResetCenterView.setOnClickListener {
            updateTitleBar {
                resetCenterView()
            }
        }
        binding.btnAddCenterView.setOnClickListener {
            updateTitleBar {
                addCenterView(createTextView())
            }
        }
        binding.rgCenterGravity.setOnCheckedChangeListener { _, checkedId ->
            val gravity = when (checkedId) {
                R.id.rg_center_gravity_start -> Gravity.START
                R.id.rg_center_gravity_center -> Gravity.CENTER
                R.id.rg_center_gravity_right -> Gravity.END
                else -> Gravity.CENTER
            }
            updateTitleBar {
                setCenterGravity(gravity)
            }
        }
    }

    private fun initRight() {
        binding.etRight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setRightButtonText(it.toString())
                }
            }
        }
        binding.etRightTextSize.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setRightButtonTextSize(12f.coerceAtLeast(it.toString().toFloat()))
                }
            }
        }
        binding.btnRightIcon.setOnClickListener {
            updateTitleBar {
                setRightButtonIcon(R.drawable.icon_back)
            }
        }
        binding.btnRightTextColor.setOnClickListener {
            updateTitleBar {
                setRightButtonTextColor(colors.random())
            }
        }
        binding.btnRightTextTypeface.setOnClickListener {
            updateTitleBar {
                if (!isRightBold) {
                    setRightButtonTypeface(Typeface.DEFAULT_BOLD)
                } else {
                    setRightButtonTypeface(Typeface.DEFAULT)
                }
                isRightBold = !isRightBold
            }
        }
        binding.etRightPaddingLeft.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightPadding()
            }
        }
        binding.etRightPaddingTop.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightPadding()
            }
        }
        binding.etRightPaddingRight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightPadding()
            }
        }
        binding.etRightPaddingBottom.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightPadding()
            }
        }
        binding.etRightMarginLeft.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightMargin()
            }
        }
        binding.etRightMarginTop.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightMargin()
            }
        }
        binding.etRightMarginRight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightMargin()
            }
        }
        binding.etRightMarginBottom.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateRightMargin()
            }
        }
        binding.btnApplyRightBtn.setOnClickListener {
            updateTitleBar {
                applyRightButton {
                    it.setText("More")
                }
            }
        }
        binding.btnApplyRightLayout.setOnClickListener {
            updateTitleBar {
                applyRightLayout {
                    it.setHorizontalGravity(Gravity.START)
                }
            }
        }
        binding.btnSetRightView.setOnClickListener {
            updateTitleBar {
                setRightView(createTextView())
            }
        }
        binding.btnResetRightView.setOnClickListener {
            updateTitleBar {
                resetRightView()
            }
        }
        binding.btnAddRightView.setOnClickListener {
            updateTitleBar {
                addRightView(createTextView())
            }
        }
        binding.rgRightGravity.setOnCheckedChangeListener { _, checkedId ->
            val gravity = when (checkedId) {
                R.id.rg_right_gravity_start -> Gravity.START
                R.id.rg_right_gravity_center -> Gravity.CENTER
                R.id.rg_right_gravity_right -> Gravity.END
                else -> Gravity.CENTER
            }
            updateTitleBar {
                setRightGravity(gravity)
            }
        }
    }

    private fun initShadow() {
        binding.btnShadowColor.setOnClickListener {
            updateTitleBar { setShadowColor(colors.random()) }
        }
        binding.btnShadowIamge.setOnClickListener {
            updateTitleBar { setShadowDrawable(R.drawable.shadow_titlebar) }
        }
        binding.etShadowHeight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setShadowHeight(1.dp.coerceAtLeast(it.toString().toInt().dp))
                }
            }
        }
        binding.btnShadowGradient.setOnClickListener {
            updateTitleBar {
                withGradient(!shadowGradient)
            }
            shadowGradient = !shadowGradient
        }
        binding.btnShadowShow.setOnClickListener {
            updateTitleBar {
                withShadow(!showShadow)
            }
            showShadow = !showShadow
        }
    }

    private fun initBackground() {
        binding.btnTitlebarBgColor.setOnClickListener {
            updateTitleBar {
                setTitleBarBackgroundColor(bgs.random())
            }
        }
        binding.btnTitlebarBgImage.setOnClickListener {
            updateTitleBar {
                setTitleBarBackground(R.drawable.bg_titlebar)
            }
        }
        binding.btnLeftBgColor.setOnClickListener {
            updateTitleBar {
                setLeftLayoutBackgroundColor(bgs.random())
            }
        }
        binding.btnLeftBgImage.setOnClickListener {
            updateTitleBar {
                setLeftLayoutBackground(R.drawable.bg_titlebar)
            }
        }
        binding.btnCenterBgColor.setOnClickListener {
            updateTitleBar {
                setCenterLayoutBackgroundColor(bgs.random())
            }
        }
        binding.btnCenterBgImage.setOnClickListener {
            updateTitleBar {
                setCenterLayoutBackground(R.drawable.bg_titlebar)
            }
        }
        binding.btnRightBgColor.setOnClickListener {
            updateTitleBar {
                setRightLayoutBackgroundColor(bgs.random())
            }
        }
        binding.btnRightBgImage.setOnClickListener {
            updateTitleBar {
                setRightLayoutBackground(R.drawable.bg_titlebar)
            }
        }
        binding.seekbarAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateTitleBar {
                    setTitleBarAlpha(progress / 100f)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun initCommon() {
        binding.btnShowTitleBar.setOnClickListener {
            updateTitleBar {
                if (showTitleBar) hide() else show()
                showTitleBar = !showTitleBar
            }
        }
        binding.btnOverlapStatusbar.setOnClickListener {
            updateTitleBar {
                fitsSystemWindows(!isFitSystemWindows)
                isFitSystemWindows = !isFitSystemWindows
            }
        }
        binding.btnOverlapContent.setOnClickListener {
            updateTitleBar {
                overlapTitleBar(!isOverlapTitleBar)
                isOverlapTitleBar = !isOverlapTitleBar
            }
        }
        binding.etTitleBarHeight.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                updateTitleBar {
                    setTitleBarHeight(48.dp.coerceAtLeast(it.toString().toInt().dp))
                }
            }
        }
        binding.btnShowLeft.setOnClickListener {
            updateTitleBar {
                hasLeftButton(!showLeft)
                showLeft = !showLeft
            }
        }
        binding.btnShowCenter.setOnClickListener {
            updateTitleBar {
                hasCenter(!showCenter)
                showCenter = !showCenter
            }
        }
        binding.btnShowRight.setOnClickListener {
            updateTitleBar {
                hasRightButton(!showRight)
                showRight = !showRight
            }
        }
    }

    private fun createTextView(): TextView {
        val textView = TextView(this)
        textView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
        textView.text = "Custom"
        textView.gravity = Gravity.START or Gravity.BOTTOM
        textView.setTextColor(Color.RED)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        return textView
    }

    override fun onLeftClick(index: Int, view: View, layout: LinearLayout) {
        Toast.makeText(this, "On left click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show()
    }

    override fun onCenterClick(index: Int, view: View, layout: LinearLayout) {
        Toast.makeText(this, "On center click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show()
    }

    override fun onRightClick(index: Int, view: View, layout: LinearLayout) {
        Toast.makeText(this, "On right click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show()
    }
}