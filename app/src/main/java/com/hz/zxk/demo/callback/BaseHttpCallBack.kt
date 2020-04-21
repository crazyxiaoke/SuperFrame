package com.hz.zxk.demo.callback

import com.hz.zxk.demo.ui.main.model.BaseModel
import com.hz.zxk.superhttp_kotlin.listener.SuperCallback

/**
@author zhengxiaoke
@date 2020/4/15 11:38 AM
 */
abstract class BaseHttpCallBack<T : BaseModel<*>>() :
    SuperCallback<T>() {


    override fun onError(e: Throwable) {
//        if (showLoading) {
//            //隐藏加载框
//            viewModel.stateLiveData.value = BaseResultModel.hideLoading()
//        }
//        val exception = SuperException.handleException(e)
//        viewModel.stateLiveData.value = BaseResultModel.error(exception.code!!, exception.message)
    }

    override fun onStart() {

    }

    override fun onSuccess(data: T?) {

//        if (showLoading) {
//            //隐藏加载框
//            viewModel.stateLiveData.value = BaseResultModel.hiedeLoading()
//        }
//        if (data == null) {
//            viewModel.stateLiveData.value = BaseResultModel.error(-1, "未知错误")
//        } else {
//            if (data.state == BaseModel.OK) {
        data?.let {
            val result = data as BaseModel<*>
            success(data)
        }


//            } else {
//                viewModel.stateLiveData.value = BaseResultModel.error(data.state, data.msg)
//            }
//    }
    }

    abstract fun success(data: T?)
}