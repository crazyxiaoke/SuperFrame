package com.hz.zxk.demo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hz.zxk.demo.R
import com.hz.zxk.superframe_kotlin.base.BaseLazyFragment
import kotlinx.android.synthetic.main.fragment_shop.*

/**
@author zhengxiaoke
@date 2019-11-24 12:11
 */
class ShopFragment : BaseLazyFragment() {
    override fun lazyInit() {
        Log.d("TAG", "懒加载")
        tv_shop.text = "我是懒加载的购物圈"
    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun init(savedInstanceState: Bundle?) {

    }
}