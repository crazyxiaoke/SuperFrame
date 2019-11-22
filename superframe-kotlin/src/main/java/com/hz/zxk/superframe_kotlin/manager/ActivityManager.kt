package com.hz.zxk.superframe_kotlin.manager

import android.app.Activity
import java.util.*

/**
@author zhengxiaoke
@date 2019-11-20 10:05
 */
class ActivityManager private constructor() {
    private var activities: Stack<Activity> = Stack()

    companion object {
        val instance: ActivityManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityManager()
        }
    }

    /**
     * 添加activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    /**
     * 移除堆栈中的activity
     */
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    /**
     * 获取栈顶的activity
     */
    fun getTopActivity(): Activity? {
        if (activities.size > 0)
            return activities[0]
        return null
    }

    /**
     * 退出app
     */
    fun exitApp() {
        for (activity in activities) {
            activity.finish()
        }
        activities.clear()
    }
}