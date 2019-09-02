package com.mall.demo.base.util

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.Window

/**
 * @author LowAndroider
 * @date 2019/8/30
 */
object ActivityUtils {

    private val tag: String = ActivityUtils.javaClass.simpleName

    fun setAndroidNativeLightStatusBar(activity: Activity, dark: Boolean) {
        val decor = activity.window.decorView
        if (dark) {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    fun error(msg: String) {
        Log.e(tag, msg)
    }

    fun error(tag: String, msg: String) {
        Log.e(tag, msg)
    }
}
