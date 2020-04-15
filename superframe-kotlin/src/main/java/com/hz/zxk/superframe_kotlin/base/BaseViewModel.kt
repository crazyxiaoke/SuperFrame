package com.hz.zxk.superframe_kotlin.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel() {
    var stateLiveData= MutableLiveData<BaseResultModel>()
}