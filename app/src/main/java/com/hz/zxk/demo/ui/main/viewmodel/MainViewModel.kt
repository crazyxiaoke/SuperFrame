package com.hz.zxk.demo.ui.main.viewmodel

import com.hz.zxk.superframe_kotlin.base.BaseResultModel
import com.hz.zxk.superframe_kotlin.base.BaseViewModel

/**
@author zhengxiaoke
@date 2019-11-23 23:25
 */
class MainViewModel: BaseViewModel() {

    fun getMainData(){

    }

    fun onLoading(){
        liveData.value=BaseResultModel.loading()
    }

    fun showToast(){
        liveData.value=BaseResultModel.toast("我是从ViewModel来的Toast")
    }
}