package com.hz.zxk.superframe_kotlin.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hz.zxk.superframe_kotlin.di.BaseViewModel
import com.hz.zxk.superframe_kotlin.manager.ActivityManager
import com.hz.zxk.superframe_kotlin.utils.LoadingDialog
import com.hz.zxk.superframe_kotlin.utils.StatusBarUtil
import com.hz.zxk.superframe_kotlin.utils.ToastUtil
import java.lang.reflect.ParameterizedType

/**
@author zhengxiaoke
@date 2019-11-20 10:03
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {
    private var fm = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        onBeforeCreate()
        super.onCreate(savedInstanceState)
        //设置状态栏
        setStatusBar()
        //设置状态栏字体颜色
        StatusBarUtil.setStatusTextLight(this, isLightStatusBar())
        bindView(savedInstanceState)
        StatusBarUtil.setRootView(this)
        //添加Activity到堆栈
        ActivityManager.instance.addActivity(this)

        onBeforeInit()
        init()
    }


    open fun setStatusBar() {
        StatusBarUtil.transparent(this)
    }

    open fun isLightStatusBar(): Boolean {
        return false
    }

    override fun onDestroy() {
        //移除在堆栈中的Activity
        ActivityManager.instance.removeActivity(this)
        super.onDestroy()
    }

    /**
     * 添加fragment
     * @param resId
     * @param fragment
     */
    fun addFragment(@IdRes resId: Int, fragment: Fragment) {
        val ft = fm.beginTransaction()
        ft.add(resId, fragment)
        ft.commitAllowingStateLoss()
    }

    /**
     * 替换fragment
     * @param resId
     * @param fragment
     */
    fun replaceFragment(@IdRes resId: Int, fragment: Fragment) {
        val ft = fm.beginTransaction()
        ft.replace(resId, fragment)
        ft.commitAllowingStateLoss()
    }

    /**
     * 移除fragment
     * @param fragment
     */
    fun removeFragment(fragment: Fragment) {
        val ft = fm.beginTransaction()
        ft.remove(fragment)
        ft.commitAllowingStateLoss()
    }

    /**
     * 显示fragment
     * @param fragment
     */
    fun showFragment(fragment: Fragment) {
        val ft = fm.beginTransaction()
        ft.show(fragment)
        ft.commitAllowingStateLoss()
    }

    /**
     * 隐藏fragment
     */
    fun hideFragment(fragment: Fragment) {
        val ft = fm.beginTransaction()
        ft.hide(fragment)
        ft.commitAllowingStateLoss()
    }

    override fun showLoading() {
        LoadingDialog.instance.show(this)
    }

    override fun showLoading(msg: String?) {
        LoadingDialog.instance.show(this, msg)
    }

    override fun hideLoading() {
        LoadingDialog.instance.dimiss()
    }

    override fun showError(code: Int, msg: String?) {
        ToastUtil.instance.showToast(this, msg)
    }


    /**
     *  init的前置函数
     *  放入一些需要在init之前执行的操作
     */
    open fun onBeforeInit() {

    }

    open fun onBeforeCreate() {}

    abstract fun bindView(savedInstanceState: Bundle?);
    abstract fun init();

}