package com.hz.zxk.superframe_kotlin.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hz.zxk.superframe_kotlin.utils.ToastUtil
import java.lang.reflect.ParameterizedType

/**
@author zhengxiaoke
@date 2019-11-24 11:50
 需要使用ViewModel的懒加载fragment
 */
abstract class BaseViewModelLazyFragment<VM : BaseViewModel> : BaseLazyFragment() {
    protected lateinit var model: VM
    override fun onBeforeInit(savedInstanceState: Bundle?) {
        super.onBeforeInit(savedInstanceState)
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
                    activity?.let { activity ->
                        ToastUtil.instance.showToast(activity, it.msg)
                    }

                }
                Statue.ERROR -> {
                    showError(it.code, it.msg)
                }
            }
        })
    }

    private fun initModel() {
        val clazz = javaClass
        val type = clazz.genericSuperclass
        if (type != null && type is ParameterizedType) {
            val viewmodelClass = type.actualTypeArguments[0] as Class<VM>
            model = ViewModelProviders.of(this).get(viewmodelClass)
        }
    }
}