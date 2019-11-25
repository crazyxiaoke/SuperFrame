package com.hz.zxk.superframe_kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hz.zxk.superframe_kotlin.utils.LoadingDialog
import com.hz.zxk.superframe_kotlin.utils.ToastUtil

/**
@author zhengxiaoke
@date 2019-11-24 11:23
 */
abstract class BaseFragment : Fragment(), IBaseView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        beforeInit(savedInstanceState)
        init(savedInstanceState)
    }


    override fun showLoading() {
        activity?.let { LoadingDialog.instance.show(it) }
    }

    override fun showLoading(msg: String?) {
        activity?.let { LoadingDialog.instance.show(it, msg) }
    }

    override fun hideLoading() {
        LoadingDialog.instance.dimiss()
    }

    override fun showError(code: Int, msg: String?) {
        activity?.let { ToastUtil.instance.showToast(it, msg) }
    }

    /**
     *  init的前置函数
     *  放入一些需要在init之前执行的操作
     */
    open fun beforeInit(savedInstanceState: Bundle?) {

    }

    abstract fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    abstract fun init(savedInstanceState: Bundle?)
}