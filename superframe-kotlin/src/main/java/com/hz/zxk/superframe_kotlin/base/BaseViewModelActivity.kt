package com.hz.zxk.superframe_kotlin.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hz.zxk.superframe_kotlin.di.BaseViewModel
import com.hz.zxk.superframe_kotlin.utils.ToastUtil
import java.lang.reflect.ParameterizedType

/**
@author zhengxiaoke
@date 2019-11-23 23:23
 */
abstract class BaseViewModelActivity<VM:BaseViewModel>:BaseActivity() {

    protected lateinit var model:VM

    private fun initModel(){
        val clazz=javaClass
        val type=clazz.genericSuperclass
        if(type!=null&&type is ParameterizedType){
            val viewmodelClass=type.actualTypeArguments[0] as Class<VM>
            model= ViewModelProviders.of(this).get(viewmodelClass)
        }
    }

    override fun beforeInit() {
        super.beforeInit()
        initModel()
        model.liveData.observe(this, Observer {
            when (it.statue) {
                Statue.LOADING -> {
                    showLoading()
                }
                Statue.LOADING_HAS_MSG -> {
                    showLoading(it.msg)
                }
                Statue.TOAST -> {
                    ToastUtil.instance.showToast(this,it.msg)
                }
                Statue.ERROR -> {
                    showError(it.code,it.msg)
                }
            }
        })
    }
}

