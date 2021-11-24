package com.ricky.etitlebar.demo

import android.content.Intent
import android.os.Bundle
import com.ricky.etitlebar.demo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnKotlinDemo.setOnClickListener {
            startActivity(Intent(this, KotlinDemoActivity::class.java))
        }
        binding.btnJavaDemo.setOnClickListener {
            startActivity(Intent(this, JavaDemoActivity::class.java))
        }
        updateTitleBar {
            hasLeftButton(false)
        }
    }
}