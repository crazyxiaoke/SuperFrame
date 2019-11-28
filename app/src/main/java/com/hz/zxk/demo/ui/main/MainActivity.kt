package com.hz.zxk.demo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.hz.zxk.demo.R
import com.hz.zxk.demo.databinding.ActivityMainBinding
import com.hz.zxk.demo.ui.main.viewmodel.MainViewModel
import com.hz.zxk.superframe_kotlin.base.BaseViewModelActivity
import com.hz.zxk.superframe_kotlin.utils.SharedPreferenceUtil
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