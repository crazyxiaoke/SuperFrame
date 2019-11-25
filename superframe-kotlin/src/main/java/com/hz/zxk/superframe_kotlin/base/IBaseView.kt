package com.hz.zxk.superframe_kotlin.base

/**
@author zhengxiaoke
@date 2019-11-23 21:13
 */
interface IBaseView {
    fun showLoading()

    fun showLoading(msg:String?)

    fun hideLoading()

    fun showError(code:Int,msg:String?)

}