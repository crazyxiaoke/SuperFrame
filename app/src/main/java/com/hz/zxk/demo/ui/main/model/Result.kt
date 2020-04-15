package com.hz.zxk.demo.ui.main.model

class Result(var state: Int, var msg: String) {

    override fun toString(): String {
        return "Result(code=$state, msg='$msg')"
    }
}