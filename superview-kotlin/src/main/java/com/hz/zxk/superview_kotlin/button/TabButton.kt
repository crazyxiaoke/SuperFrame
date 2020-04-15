package com.hz.zxk.superview_kotlin.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hz.zxk.superview_kotlin.R
import com.hz.zxk.superview_kotlin.util.dp2px

/**
@author zhengxiaoke
@date 2020/4/14 4:04 PM
 */
class TabButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    //normal_icon
    var normalIcon: Drawable? = null
    //selected_icon
    var selectedIcon: Drawable? = null
    // text
    var text: String? = null
    //textsize
    var textSize: Int = dp2px(14f)
    //normal_textColor
    var normalTextColor: Int = -1
    //selected_textColor
    var selectedTextColor: Int = -1

    private var ivIcon: ImageView? = null
    private var tvLabel: TextView? = null

    var fragment: Fragment? = null

    init {
        attrs?.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.TabButton)
            normalIcon = ta.getDrawable(R.styleable.TabButton_tab_normal_icon)
            selectedIcon = ta.getDrawable(R.styleable.TabButton_tab_selected_icon)
            text = ta.getString(R.styleable.TabButton_tab_text)
            textSize = ta.getDimensionPixelOffset(R.styleable.TabButton_tab_textSize, textSize)
            normalTextColor =
                ta.getColor(R.styleable.TabButton_tab_normal_textColor, normalTextColor)
            selectedTextColor =
                ta.getColor(R.styleable.TabButton_tab_selected_textColor, selectedTextColor)
        }

        initView()
    }

    private fun initView() {
        val box = LinearLayout(context)
        box.orientation = LinearLayout.VERTICAL
        val lp = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.addRule(RelativeLayout.CENTER_IN_PARENT)
        box.layoutParams = lp

        ivIcon = ImageView(context)
        normalIcon.let {
            ivIcon?.setImageDrawable(normalIcon)
        }
        box.addView(ivIcon)

        tvLabel = TextView(context)
        tvLabel?.gravity = Gravity.CENTER_HORIZONTAL
        tvLabel?.text = text
        tvLabel?.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        tvLabel?.setTextColor(normalTextColor)
        box.addView(tvLabel)

        addView(box)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        when (selected) {
            true -> {
                selectedIcon.let { ivIcon?.setImageDrawable(selectedIcon) }
                if (selectedTextColor != -1) {
                    tvLabel?.setTextColor(selectedTextColor)
                }
            }
            false -> {
                normalIcon.let { ivIcon?.setImageDrawable(normalIcon) }
                if (normalTextColor != -1) {
                    tvLabel?.setTextColor(normalTextColor)
                }
            }
        }
    }

}