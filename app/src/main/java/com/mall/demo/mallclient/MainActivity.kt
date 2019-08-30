package com.mall.demo.mallclient

import android.os.Bundle
import com.mall.demo.base.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun isDarkTheme(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}