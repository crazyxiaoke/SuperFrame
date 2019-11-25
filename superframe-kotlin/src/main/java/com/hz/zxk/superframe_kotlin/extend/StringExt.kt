package com.hz.zxk.superframe_kotlin.extend

/**
@author zhengxiaoke
@date 2019-11-24 13:58
 */
/**
 * 是否是手机号
 */
fun String.isMobile(): Boolean {
    val regex = Regex("^[1]\\d{10}$")
    return regex.matches(this)
}

/**
 * 是否是邮箱
 */
fun String.isEmail(): Boolean {
    val regex = Regex("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
    return regex.matches(this)
}
