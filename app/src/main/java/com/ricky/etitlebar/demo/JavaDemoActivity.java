package com.ricky.etitlebar.demo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ricky.etitlebar.ETitleBar;
import com.ricky.etitlebar.ImageTextView;
import com.ricky.etitlebar.demo.databinding.ActivityDemoBinding;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author haiyanghou
 * @date 2021/11/24
 */
public class JavaDemoActivity extends BaseActivity {
    private ArrayList<Integer> colors = new ArrayList<Integer>();
    private ArrayList<Integer> bgs = new ArrayList<Integer>();
    private ActivityDemoBinding binding;
    private Boolean isLeftBold = false;
    private Boolean isTitleBold = false;
    private Boolean isRightBold = false;
    private Boolean shadowGradient = true;
    private Boolean showShadow = true;
    private Boolean showTitleBar = true;
    private Boolean isFitSystemWindows = true;
    private Boolean isOverlapTitleBar = false;
    private Boolean showLeft = true;
    private Boolean showCenter = true;
    private Boolean showRight = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        binding = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initLeft();
        initCenter();
        initRight();
        initShadow();
        initBackground();
        initCommon();
    }

    private void init() {
        colors.add(Color.BLACK);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);

        bgs.add(Color.WHITE);
        bgs.add(Color.RED);
        bgs.add(Color.GRAY);
        bgs.add(Color.GREEN);
        bgs.add(Color.CYAN);
        bgs.add(Color.YELLOW);
    }

    private void initLeft() {
        binding.etLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setLeftButtonText(s.toString()).update();
                    }
                }
            }
        });
        binding.etLeftTextSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setLeftButtonTextSize(Math.max(pix2Dp(12), pix2Dp(Integer.parseInt(s.toString())))).update();
                    }
                }
            }
        });
        binding.btnLeftIcon.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setLeftButtonIcon(R.drawable.icon_back).update();
            }
        });
        binding.btnLeftTextColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(colors.size());
                titleBar.setLeftButtonTextColor(colors.get(index)).update();
            }
        });
        binding.btnLeftTextTypeface.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                if (!isLeftBold) {
                    titleBar.setLeftButtonTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    titleBar.setLeftButtonTypeface(Typeface.DEFAULT);
                }
                isLeftBold = !isLeftBold;
                titleBar.update();
            }
        });
        binding.etLeftPaddingLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftPadding();
            }
        });
        binding.etLeftPaddingTop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftPadding();

            }
        });
        binding.etLeftPaddingRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftPadding();
            }
        });
        binding.etLeftPaddingBottom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftPadding();
            }
        });
        binding.etLeftMarginLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftMargin();
            }
        });
        binding.etLeftMarginTop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftMargin();
            }
        });
        binding.etLeftMarginRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftMargin();
            }
        });
        binding.etLeftMarginBottom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateLeftMargin();
            }
        });
        binding.btnApplyLeftBtn.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.getLeftButton().setText("Cancel");
                titleBar.update();
            }
        });
        binding.btnApplyLeftLayout.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.getLeftLayout().setHorizontalGravity(Gravity.END);
                titleBar.update();
            }
        });
        binding.btnSetLeftView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setLeftView(createTextView()).update();
            }
        });
        binding.btnResetLeftView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.resetLeftView().update();
            }
        });
        binding.btnAddLeftView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.addLeftView(createTextView()).update();
            }
        });
        binding.rgLeftGravity.setOnCheckedChangeListener((group, checkedId) -> {
            int gravity = Gravity.CENTER;
            if (checkedId == R.id.rg_left_gravity_start) {
                gravity = Gravity.START;
            } else if (checkedId == R.id.rg_left_gravity_right) {
                gravity = Gravity.END;
            }
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setLeftGravity(gravity).update();
            }
        });
    }

    private void updateLeftPadding() {
        String leftText = binding.etLeftPaddingLeft.getText().toString();
        int left = TextUtils.isEmpty(leftText) ? 0 : Integer.parseInt(leftText);
        String topText = binding.etLeftPaddingTop.getText().toString();
        int top = TextUtils.isEmpty(topText) ? 0 : Integer.parseInt(topText);
        String rightText = binding.etLeftPaddingRight.getText().toString();
        int right = TextUtils.isEmpty(rightText) ? 0 : Integer.parseInt(rightText);
        String bottomText = binding.etLeftPaddingBottom.getText().toString();
        int bottom = TextUtils.isEmpty(bottomText) ? 0 : Integer.parseInt(bottomText);
        ETitleBar.Builder titleBar = getTitleBarBuilder();
        if (titleBar != null) {
            titleBar.setLeftButtonPadding(left, top, right, bottom).update();
        }
    }

    private void updateLeftMargin() {
        String leftText = binding.etLeftMarginLeft.getText().toString();
        int left = TextUtils.isEmpty(leftText) ? 0 : Integer.parseInt(leftText);
        String topText = binding.etLeftMarginTop.getText().toString();
        int top = TextUtils.isEmpty(topText) ? 0 : Integer.parseInt(topText);
        String rightText = binding.etLeftMarginRight.getText().toString();
        int right = TextUtils.isEmpty(rightText) ? 0 : Integer.parseInt(rightText);
        String bottomText = binding.etLeftMarginBottom.getText().toString();
        int bottom = TextUtils.isEmpty(bottomText) ? 0 : Integer.parseInt(bottomText);
        ETitleBar.Builder titleBar = getTitleBarBuilder();
        if (titleBar != null) {
            titleBar.setLeftButtonMargin(left, top, right, bottom).update();
        }
    }

    private void updateTitlePadding() {
        String leftText = binding.etTitlePaddingLeft.getText().toString();
        int left = TextUtils.isEmpty(leftText) ? 0 : Integer.parseInt(leftText);
        String topText = binding.etTitlePaddingTop.getText().toString();
        int top = TextUtils.isEmpty(topText) ? 0 : Integer.parseInt(topText);
        String rightText = binding.etTitlePaddingRight.getText().toString();
        int right = TextUtils.isEmpty(rightText) ? 0 : Integer.parseInt(rightText);
        String bottomText = binding.etTitlePaddingBottom.getText().toString();
        int bottom = TextUtils.isEmpty(bottomText) ? 0 : Integer.parseInt(bottomText);
        ETitleBar.Builder titleBar = getTitleBarBuilder();
        if (titleBar != null) {
            titleBar.setTitleViewPadding(left, top, right, bottom).update();
        }
    }

    private void updateTitleMargin() {
        String leftText = binding.etTitleMarginLeft.getText().toString();
        int left = TextUtils.isEmpty(leftText) ? 0 : Integer.parseInt(leftText);
        String topText = binding.etTitleMarginTop.getText().toString();
        int top = TextUtils.isEmpty(topText) ? 0 : Integer.parseInt(topText);
        String rightText = binding.etTitleMarginRight.getText().toString();
        int right = TextUtils.isEmpty(rightText) ? 0 : Integer.parseInt(rightText);
        String bottomText = binding.etTitleMarginBottom.getText().toString();
        int bottom = TextUtils.isEmpty(bottomText) ? 0 : Integer.parseInt(bottomText);
        ETitleBar.Builder titleBar = getTitleBarBuilder();
        if (titleBar != null) {
            titleBar.setTitleViewMargin(left, top, right, bottom).update();
        }
    }

    private void updateRightPadding() {
        String leftText = binding.etRightPaddingLeft.getText().toString();
        int left = TextUtils.isEmpty(leftText) ? 0 : Integer.parseInt(leftText);
        String topText = binding.etRightPaddingTop.getText().toString();
        int top = TextUtils.isEmpty(topText) ? 0 : Integer.parseInt(topText);
        String rightText = binding.etRightPaddingRight.getText().toString();
        int right = TextUtils.isEmpty(rightText) ? 0 : Integer.parseInt(rightText);
        String bottomText = binding.etTitlePaddingBottom.getText().toString();
        int bottom = TextUtils.isEmpty(bottomText) ? 0 : Integer.parseInt(bottomText);
        ETitleBar.Builder titleBar = getTitleBarBuilder();
        if (titleBar != null) {
            titleBar.setRightButtonPadding(left, top, right, bottom).update();
        }
    }

    private void updateRightMargin() {
        String leftText = binding.etRightMarginLeft.getText().toString();
        int left = TextUtils.isEmpty(leftText) ? 0 : Integer.parseInt(leftText);
        String topText = binding.etRightMarginTop.getText().toString();
        int top = TextUtils.isEmpty(topText) ? 0 : Integer.parseInt(topText);
        String rightText = binding.etRightMarginRight.getText().toString();
        int right = TextUtils.isEmpty(rightText) ? 0 : Integer.parseInt(rightText);
        String bottomText = binding.etRightMarginBottom.getText().toString();
        int bottom = TextUtils.isEmpty(bottomText) ? 0 : Integer.parseInt(bottomText);
        ETitleBar.Builder titleBar = getTitleBarBuilder();
        if (titleBar != null) {
            titleBar.setRightButtonMargin(left, top, right, bottom).update();
        }
    }


    private void initCenter() {
        binding.etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setTitle(s.toString()).update();
                    }
                }
            }
        });
        binding.btnTitleColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(colors.size());
                titleBar.setTitleColor(colors.get(index)).update();
            }
        });
        binding.etCenterTextSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setTitleTextSize(Math.max(pix2Dp(12), pix2Dp(Integer.parseInt(s.toString())))).update();
                    }
                }
            }
        });
        binding.btnTitleTextTypeface.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                if (!isTitleBold) {
                    titleBar.setTitleTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    titleBar.setTitleTypeface(Typeface.DEFAULT);
                }
                isTitleBold = !isTitleBold;
                titleBar.update();
            }
        });
        binding.etTitlePaddingLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitlePadding();
            }
        });
        binding.etTitlePaddingTop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitlePadding();
            }
        });
        binding.etTitlePaddingRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitlePadding();
            }
        });
        binding.etTitlePaddingBottom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitlePadding();
            }
        });
        binding.etTitleMarginLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitleMargin();
            }
        });
        binding.etTitleMarginTop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitleMargin();
            }
        });
        binding.etTitleMarginRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitleMargin();
            }
        });
        binding.etTitleMarginBottom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateTitleMargin();
            }
        });
        binding.btnApplyTitle.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.getTitleView().setText("Hello world");
                titleBar.update();
            }
        });
        binding.btnApplyCenterLayout.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.getCenterLayout().setHorizontalGravity(Gravity.START);
                titleBar.update();
            }
        });
        binding.btnSetCenterView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setCenterView(createTextView()).update();
            }
        });
        binding.btnResetCenterView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.resetCenterView().update();
            }
        });
        binding.btnAddCenterView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.addCenterView(createTextView()).update();
            }
        });
        binding.rgCenterGravity.setOnCheckedChangeListener((group, checkedId) -> {
            int gravity = Gravity.CENTER;
            if (checkedId == R.id.rg_center_gravity_start) {
                gravity = Gravity.START;
            } else if (checkedId == R.id.rg_center_gravity_right) {
                gravity = Gravity.END;
            }
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setCenterGravity(gravity).update();
            }
        });
    }

    private void initRight() {
        binding.etRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setRightButtonText(s.toString()).update();
                    }
                }
            }
        });

        binding.etRightTextSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setRightButtonTextSize(Math.max(pix2Dp(12), pix2Dp(Integer.parseInt(s.toString())))).update();
                    }
                }
            }
        });
        binding.btnRightIcon.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setRightButtonIcon(R.drawable.icon_back).update();
            }
        });
        binding.btnRightTextColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(colors.size());
                titleBar.setRightButtonTextColor(colors.get(index)).update();
            }
        });
        binding.btnRightTextTypeface.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                if (!isRightBold) {
                    titleBar.setRightButtonTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    titleBar.setRightButtonTypeface(Typeface.DEFAULT);
                }
                isRightBold = !isRightBold;
                titleBar.update();
            }
        });
        binding.etRightPaddingLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightPadding();
            }
        });
        binding.etRightPaddingTop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightPadding();
            }
        });
        binding.etRightPaddingRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightPadding();
            }
        });
        binding.etRightPaddingBottom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightPadding();
            }
        });
        binding.etRightMarginLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightMargin();
            }
        });
        binding.etRightMarginTop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightMargin();
            }
        });
        binding.etRightMarginRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightMargin();
            }
        });
        binding.etRightMarginBottom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) updateRightMargin();
            }
        });
        binding.btnApplyRightBtn.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                ImageTextView button = titleBar.getRightButton();
                button.setText("More");
                titleBar.update();
            }
        });
        binding.btnApplyRightLayout.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                LinearLayout rightLayout = titleBar.getRightLayout();
                rightLayout.setHorizontalGravity(Gravity.START);
                titleBar.update();
            }
        });
        binding.btnSetRightView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setRightView(createTextView()).update();
            }
        });
        binding.btnResetRightView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.resetRightView().update();
            }
        });
        binding.btnAddRightView.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.addRightView(createTextView()).update();
            }
        });
        binding.rgRightGravity.setOnCheckedChangeListener((group, checkedId) -> {
            int gravity = Gravity.CENTER;
            if (checkedId == R.id.rg_right_gravity_start) {
                gravity = Gravity.START;
            } else if (checkedId == R.id.rg_right_gravity_right) {
                gravity = Gravity.END;
            }
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setRightGravity(gravity).update();
            }
        });
    }

    private void initShadow() {
        binding.btnShadowColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(colors.size());
                titleBar.setShadowColor(colors.get(index)).update();
            }
        });
        binding.btnShadowIamge.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setShadowDrawable(R.drawable.shadow_titlebar).update();
            }
        });
        binding.etShadowHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setShadowHeight(Math.max(pix2Dp(1), pix2Dp(Integer.parseInt(s.toString())))).update();
                    }
                }
            }
        });
        binding.btnShadowGradient.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.withGradient(!shadowGradient).update();
                shadowGradient = !shadowGradient;
            }
        });
        binding.btnShadowShow.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.withShadow(!showShadow).update();
                showShadow = !showShadow;
            }
        });
    }

    private void initBackground() {
        binding.btnTitlebarBgColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(bgs.size());
                titleBar.setTitleBarBackgroundColor(bgs.get(index)).update();
            }
        });
        binding.btnTitlebarBgImage.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setTitleBarBackground(R.drawable.bg_titlebar).update();
            }
        });
        binding.btnLeftBgColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(bgs.size());
                titleBar.setLeftLayoutBackgroundColor(bgs.get(index)).update();
            }
        });
        binding.btnLeftBgImage.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setLeftLayoutBackground(R.drawable.bg_titlebar).update();
            }
        });
        binding.btnCenterBgColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(bgs.size());
                titleBar.setCenterLayoutBackgroundColor(bgs.get(index)).update();
            }
        });
        binding.btnCenterBgImage.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setCenterLayoutBackground(R.drawable.bg_titlebar).update();
            }
        });
        binding.btnRightBgColor.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                int index = new Random().nextInt(bgs.size());
                titleBar.setRightLayoutBackgroundColor(bgs.get(index)).update();
            }
        });
        binding.btnRightBgImage.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.setRightLayoutBackground(R.drawable.bg_titlebar).update();
            }
        });
        binding.seekbarAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ETitleBar.Builder titleBar = getTitleBarBuilder();
                if (titleBar != null) {
                    titleBar.setTitleBarAlpha(progress / 100f).update();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initCommon() {
        binding.btnShowTitleBar.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                if (showTitleBar) titleBar.hide().update();
                else titleBar.show().update();
                showTitleBar = !showTitleBar;
            }
        });
        binding.btnOverlapStatusbar.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.fitsSystemWindows(!isFitSystemWindows).update();
                isFitSystemWindows = !isFitSystemWindows;
            }
        });
        binding.btnOverlapContent.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.overlapTitleBar(!isOverlapTitleBar).update();
                isOverlapTitleBar = !isOverlapTitleBar;
            }
        });
        binding.etTitleBarHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    ETitleBar.Builder titleBar = getTitleBarBuilder();
                    if (titleBar != null) {
                        titleBar.setTitleBarHeight(Math.max(pix2Dp(48), pix2Dp(Integer.parseInt(s.toString())))).update();
                        showLeft = !showLeft;
                    }
                }
            }
        });
        binding.btnShowLeft.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.hasLeftButton(!showLeft).update();
                showLeft = !showLeft;
            }
        });
        binding.btnShowCenter.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.hasCenter(!showCenter).update();
                showRight = !showRight;
            }
        });
        binding.btnShowRight.setOnClickListener(v -> {
            ETitleBar.Builder titleBar = getTitleBarBuilder();
            if (titleBar != null) {
                titleBar.hasRightButton(!showRight).update();
                showRight = !showRight;
            }
        });
    }

    private int pix2Dp(int pix) {
        return pix;
    }

    private TextView createTextView() {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setText("Custom");
        textView.setGravity(Gravity.START | Gravity.BOTTOM);
        textView.setTextColor(Color.RED);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
        return textView;
    }

    @Override
    public void onLeftClick(int index, @NonNull View view, @NonNull LinearLayout layout) {
        Toast.makeText(this, "On left click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCenterClick(int index, @NonNull View view, @NonNull LinearLayout layout) {
        Toast.makeText(this, "On center click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightClick(int index, @NonNull View view, @NonNull LinearLayout layout) {
        Toast.makeText(this, "On right click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show();
    }
}
