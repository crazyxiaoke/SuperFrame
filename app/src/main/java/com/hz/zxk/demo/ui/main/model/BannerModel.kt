package com.hz.zxk.demo.ui.main.model

import com.google.gson.annotations.SerializedName

/**
@author zhengxiaoke
@date 2020/4/15 9:22 AM
 */
class BannerModel {
    @SerializedName("title_")
    var title: String? = null
    @SerializedName("path_")
    var path: String? = null
    @SerializedName("content_")
    var content: String? = null
    @SerializedName("link_path_")
    var linkPath: String? = null

    override fun toString(): String {
        return "BannerModel(title=$title, path=$path, content=$content, linkPath=$linkPath)"
    }


}