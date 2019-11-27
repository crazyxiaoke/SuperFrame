package com.hz.zxk.demo.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hz.zxk.demo.R

@Route(path = "/shop/shop")
class ShopListActivity : AppCompatActivity() {


//    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        ARouter.getInstance().inject(this)
//        Log.d("TAG", "name=${name}")
    }
}
