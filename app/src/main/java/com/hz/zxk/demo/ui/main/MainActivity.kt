package com.hz.zxk.demo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.hz.zxk.demo.R
import com.hz.zxk.demo.callback.BaseHttpCallBack
import com.hz.zxk.demo.databinding.ActivityMainBinding
import com.hz.zxk.demo.ui.main.model.BaseModel
import com.hz.zxk.demo.ui.main.model.Test
import com.hz.zxk.demo.ui.main.viewmodel.MainViewModel
import com.hz.zxk.superframe_kotlin.base.BaseResultModel
import com.hz.zxk.superframe_kotlin.base.BaseViewModelActivity
import com.hz.zxk.superframe_kotlin.base.Statue
import com.hz.zxk.superframe_kotlin.utils.SharedPreferenceUtil
import com.hz.zxk.superhttp_kotlin.SuperHttp
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = "main/main")
class MainActivity : BaseViewModelActivity<MainViewModel>() {
    private var mBinding: ActivityMainBinding? = null
    private var mainFragment: MainFragment? = null
    private var shopFragment: ShopFragment? = null
    override fun bindView(savedInstanceState: Bundle?) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun init() {
        SharedPreferenceUtil.instance.init(this)
        SharedPreferenceUtil.instance.putValue("test", "这是测试").commit()
        Log.d("TAG", "TEST=" + SharedPreferenceUtil.instance.getString("test"))
        mBinding?.model = model
        initMainFragment()
        btn_main.setOnClickListener(View.OnClickListener {
            hideAllFragment()
            initMainFragment()
        })

        btn_shop.setOnClickListener(View.OnClickListener {
            hideAllFragment()
            initShopFragment()
        })

        SuperHttp.instance.method(SuperHttp.Method.POST)
            .url("app_user/2/training/find_page_info")
            .addBody(BaseResultModel(Statue.HIDE_LOADING, 1, "测试"))
            .request("traningMainInfo", object : BaseHttpCallBack<BaseModel<Test>>() {
                override fun success(data: BaseModel<Test>?) {
                    Log.d("TAG", "$data")
                }
            })
//        SuperHttp.instance
//            .url("api/v1/template/main")
//            .method(SuperHttp.Method.GET)
//            .addParam("hoho", "haha")
//            .request("", object : SuperCallback<Result>() {
//                override fun onStart() {
//                    Log.d("TAG", "onStart");
//                }
//
//                override fun onSuccess(data: Result?) {
//                    Log.d("TAG", "onSuccess");
//                    Log.d("TAG", "result=${data}")
//                }
//
//                override fun onError(e: Throwable) {
//                    e.printStackTrace()
//                    Log.d("TAG", "onError");
//                }
//            })
    }

    private fun initMainFragment() {
        if (mainFragment == null) {
            mainFragment = MainFragment()
            addFragment(R.id.fl_content, mainFragment!!)
        } else {
            showFragment(mainFragment!!)
        }

    }

    private fun initShopFragment() {
        if (shopFragment == null) {
            shopFragment = ShopFragment()
            addFragment(R.id.fl_content, shopFragment!!)
        } else {
            showFragment(shopFragment!!)
        }
    }

    private fun hideAllFragment() {
        if (mainFragment != null) {
            hideFragment(mainFragment!!)
        }
        if (shopFragment != null) {
            hideFragment(shopFragment!!)
        }
    }


    override fun isLightStatusBar(): Boolean {
        return true
    }
}
