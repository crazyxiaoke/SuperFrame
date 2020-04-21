package com.hz.zxk.demo.ui.main.model

import com.google.gson.annotations.SerializedName

/**
@author zhengxiaoke
@date 2020/4/15 5:22 PM
 */
class Test {
    @SerializedName("training_home_banner_down_prepare_list")
    var homeBannerPrepare: BannerModel? = null
    override fun toString(): String {
        return "Test(training_home_banner_down_prepare_list=$homeBannerPrepare)"
    }


}