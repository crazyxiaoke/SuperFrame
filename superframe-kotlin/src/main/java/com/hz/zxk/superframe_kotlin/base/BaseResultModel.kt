package com.hz.zxk.superframe_kotlin.base

/**
@author zhengxiaoke
@date 2019-11-23 23:10
 */
open class BaseResultModel(
    var statue: Statue,
    var code: Int = 0,
    var msg: String?
) {
    companion object {
        fun loading(): BaseResultModel {
            return BaseResultModel(Statue.LOADING, msg = "")
        }

        fun loading(msg: String?): BaseResultModel {
            return BaseResultModel(Statue.LOADING_HAS_MSG, msg = msg)
        }

        fun hiedeLoading(): BaseResultModel {
            return BaseResultModel(Statue.HIDE_LOADING, msg = "")
        }

        fun toast(msg: String?): BaseResultModel {
            return BaseResultModel(Statue.TOAST, msg = msg)
        }

        fun error(code: Int, msg: String?): BaseResultModel {
            return BaseResultModel(Statue.ERROR, code, msg)
        }
    }
}

enum class Statue {
    LOADING, LOADING_HAS_MSG, HIDE_LOADING, TOAST, ERROR
}