package com.mall.demo.base.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.telephony.TelephonyManager
import java.io.DataOutputStream
import java.io.File
import java.io.IOException
import java.lang.reflect.Method
import java.util.*

/**
 * @author LowAndroider
 *
 * @date 2019/8/30
 */
object SystemUtils {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    fun getSystemLanguage(): String? = Locale.getDefault().language


    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    fun getSystemLanguageList(): Array<Locale>? = Locale.getAvailableLocales()

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    fun getSystemVersion(): String? = Build.VERSION.RELEASE

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    fun getSystemModel(): String? = Build.MODEL

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    fun getDeviceBrand(): String? = Build.BRAND

    /**
     * 获取手机序列号
     *
     * @return 手机序列号
     */
    fun getSerialNumber(): String? {
        var serial: String? = null
        try {
            val c = Class.forName("android.os.SystemProperties")
            val get: Method = c.getMethod("get", String.javaClass)
            serial = get.invoke(c, "ro.serialno") as String?
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return serial
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    fun getIMEI(ctx: Context?): String? {
        val tm = ctx?.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager?
        return tm?.imei
    }

    /**
     * 版本名
     */
    fun getVersionName(ctx: Context?): Double? {
        return getPackageInfo(ctx)?.versionName?.toDouble()
    }

    /**
     * 版本号
     */
    fun getVersionCode(ctx: Context?):Int? {
        return getPackageInfo(ctx)?.versionCode
    }

    /**
     * 获取包名
     */
    private fun getPackageInfo(ctx: Context?): PackageInfo? {
        return try {
            val pm = ctx?.packageManager
            pm?.getPackageInfo(ctx.packageName, PackageManager.GET_CONFIGURATIONS)
        } catch (e : Exception) {
            null
        }
    }

    /**
     * 系统重启
     */
    fun reBootDevice() {
        try {
            createSuProcess("reboot")?.waitFor()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 重启设备只需要 createSuProcess("reboot").waitFor();
     */

    fun createSuProcess(cmd: String): Process? {
        var os: DataOutputStream? = null
        var process = createSuProcess()
        try {
            os = DataOutputStream(process.outputStream)
            os.writeBytes(cmd + "\n")
            os.writeBytes("exit $?\n")
        } finally {
            if (os != null) {
                try {
                    os.close()
                } catch (e: IOException) {
                }
            }
        }
        return process
    }

    fun createSuProcess() : Process{
        val rootUser = File("/system/xbin/ru")
        if (rootUser.exists()) {
            return Runtime.getRuntime().exec(rootUser.getAbsolutePath())
        } else {
            return Runtime.getRuntime().exec("su")
        }
    }
}