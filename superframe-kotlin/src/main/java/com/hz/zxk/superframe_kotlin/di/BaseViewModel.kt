package com.hz.zxk.superframe_kotlin.di

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hz.zxk.superframe_kotlin.base.BaseResultModel

/**
@author zhengxiaoke
@date 2019-11-23 22:49
 */
open class BaseViewModel:ViewModel() {
    var liveData=MutableLiveData<BaseResultModel>()


}