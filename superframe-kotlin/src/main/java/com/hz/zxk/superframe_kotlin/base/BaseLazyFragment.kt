package com.hz.zxk.superframe_kotlin.base

import android.os.Bundle

/**
@author zhengxiaoke
@date 2019-11-24 11:34
懒加载模式的fragment
 */
abstract class BaseLazyFragment : BaseFragment() {
    private var isCreate = false
    private var hasLoad = false
    private var isVisibleToUser = false

    override fun beforeInit(savedInstanceState: Bundle?) {
        super.beforeInit(savedInstanceState)
        isCreate = true
    }

    override fun onResume() {
        super.onResume()
        isVisibleToUser = true
        lazyLoad()
    }

    private fun lazyLoad() {
        if (!isCreate || !isVisibleToUser || hasLoad) {
            return
        }
        lazyInit()
        hasLoad = true
    }

    abstract fun lazyInit()
}