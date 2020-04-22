package com.hz.zxk.superview_kotlin.layout

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout
import com.hz.zxk.superview_kotlin.layout.helper.RoundLayoutHelper

/**
@author zhengxiaoke
@date 2020/4/22 3:20 PM
 */
class RoundFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleId: Int = 0
) : FrameLayout(context, attrs, defStyleId) {
    private val helper = RoundLayoutHelper(context, this, attrs)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        helper.onSizeChange(w,h)
    }


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        helper.draw(canvas)
    }
}