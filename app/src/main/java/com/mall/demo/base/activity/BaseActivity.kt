package com.mall.demo.base.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.mall.demo.base.util.ActivityUtils


/**
 * @author LowAndroider
 *
 * @date 2019/8/30
 */
abstract class BaseActivity: AppCompatActivity() {

    protected val tag: String = this.javaClass.simpleName

    open fun isDarkTheme(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtils.error("dark is ${isDarkTheme()}")
        ActivityUtils.setAndroidNativeLightStatusBar(this, isDarkTheme())
    }
}