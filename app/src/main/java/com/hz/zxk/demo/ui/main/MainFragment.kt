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
        Log.d("TAG", "启动动画")
        val valueAnimator = ValueAnimator.ofInt(2012, 2019);
        valueAnimator.addUpdateListener {
            val v = it.animatedValue
            Log.d("TAG", "v=${v}");
            text.text = v.toString()
        }
        valueAnimator.duration = 1000
        valueAnimator.start()
        Log.d("TAG", "启动动画结束")
        ImageLoader.instance.loadImage(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576582507348&di=df368bdd9be497bd22b21f489f8dc982&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F43efd35d1e9cadc6d8ff5cdc5faccec06f1082bb4efc4-o8K27E_fw658",
            image
        ) {
            radius = 360
            errorHolder = R.drawable.ic_launcher_background
            placeHolder = R.drawable.ic_launcher_foreground
        }
        btn.setOnClickListener {
            if (viewStub != null) {
                viewStub.inflate()
            }
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