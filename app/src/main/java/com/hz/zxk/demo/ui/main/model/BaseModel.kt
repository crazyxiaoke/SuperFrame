package com.hz.zxk.demo.ui.main.model

class BaseModel<T>(var state: Int, var msg: String) {
    var data: T? = null
    override fun toString(): String {
        return "Result(state=$state, msg='$msg', data=$data)"
    }

}