package com.hz.zxk.demo.ui.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hz.zxk.demo.R
import com.hz.zxk.demo.ui.shop.ShopListActivity
import com.hz.zxk.superframe_kotlin.base.BaseLazyFragment
import com.hz.zxk.superframe_kotlin.imageloader.ImageLoader
import com.hz.zxk.superframe_kotlin.router.RouterUtil
import com.hz.zxk.superframe_kotlin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_main.*

/**
@author zhengxiaoke
@date 2019-11-24 12:04
 */
class MainFragment : BaseLazyFragment() {
    override fun lazyInit() {

    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.transparent(activity!!)
        val valueAnimator = ValueAnimator.ofInt(2012, 2019);
        valueAnimator.addUpdateListener {
            val v = it.animatedValue

            text.text = v.toString()
        }
        valueAnimator.duration = 1000
        valueAnimator.start()

        btn.isEnabled = false
        btn.setOnClickListener {
            //            if (viewStub != null) {
//                viewStub.inflate()
//            }
            Log.d("TAG", "btnOnclick")
        }

        skip.setOnClickListener {
            //            StartActivityUtil.pushActivity(context!!, ShopListActivity().javaClass)
            val bundle = Bundle()
            bundle.putString("name", "李四")
//            ARouter.getInstance().build("/shop/shop").navigation()
            RouterUtil.instance.pushActivity(context!!, ShopListActivity::class.java.name)
        }


    }
}