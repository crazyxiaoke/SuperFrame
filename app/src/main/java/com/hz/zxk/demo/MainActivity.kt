package com.hz.zxk.demo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hz.zxk.demo.databinding.ActivityMainBinding
import com.hz.zxk.demo.viewmodel.MainViewModel
import com.hz.zxk.superframe_kotlin.base.BaseActivity
import com.hz.zxk.superframe_kotlin.base.BaseViewModelActivity
import com.hz.zxk.superframe_kotlin.extend.getStatusHeight
import com.hz.zxk.superframe_kotlin.extend.isEmail
import com.hz.zxk.superframe_kotlin.extend.isMobile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<MainViewModel>() {
    private var mBinding: ActivityMainBinding? = null
    var index = 0
    private var mainFragment: MainFragment? = null
    private var shopFragment: ShopFragment? = null
    override fun bindView(savedInstanceState: Bundle?) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun init() {
        val phone1:String="13409883321"
        val phone2:String="211039485888"
        Log.d("TAG","phone1.isPhone=${phone1.isMobile()}")
        Log.d("TAG","phone2.isPhone=${phone2.isMobile()}")
        Log.d("TAG", "height=" + getStatusHeight())
        val email1:String="123445@qq.com"
        val email2:String="123424qq@.com"
        Log.d("TAG","email1.isEmail=${email1.isEmail()}")
        Log.d("TAG","email2.isEmail=${email2.isEmail()}")
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
