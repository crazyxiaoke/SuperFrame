package com.hz.zxk.demo.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hz.zxk.demo.R
import com.hz.zxk.superframe_kotlin.base.BaseActivity

@Route(path = "/shop/shop")
class ShopListActivity : BaseActivity() {

    override fun init() {

    }

    override fun bindView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_shop_list)
//        ARouter.getInstance().inject(this)
    }

}
