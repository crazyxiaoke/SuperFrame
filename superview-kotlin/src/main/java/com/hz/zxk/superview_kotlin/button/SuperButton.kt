package com.hz.zxk.superview_kotlin.button

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.Button
import com.hz.zxk.superview_kotlin.R

/**
 *@Author zhengxiaoke
 *@Date 2019/12/25 14:04
 *@Description 自定义的Button
 * 可设置圆角，按压颜色、选中颜色、不可点击颜色
 * 无需再创建Drawable文件来控制触控颜色了
 */
class SuperButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    Button(context, attrs, defStyleAttr) {
    private val normalState: IntArray = intArrayOf(android.R.attr.state_enabled)
    private val pressedState: IntArray =
        intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled)
    private val selectedState: IntArray =
        intArrayOf(android.R.attr.state_selected, android.R.attr.state_enabled)
    private val enabledState: IntArray = intArrayOf(-android.R.attr.state_enabled)

    private var radius = 0
    private var topLeftRadius = 0
    private var topRightRadius = 0
    private var bottomLeftRadius = 0
    private var bottomRightRadius = 0
    //按钮背景
    private var normalBackground: Int = 0
    private var pressedBackground: Int = 0
    private var enabledBackground: Int = 0
    private var selectedBackground: Int = 0
    //按钮背景样式 1：充满 2：边框
    private var backgroundStyle = 1
    private var normalBackgroundStyle = 0
    private var pressedBackgroundStyle = 0
    private var enabledBackgroundStyle = 0
    private var selectedBackgroundStyle = 0
    //按钮边框宽度
    private var storkWidth = 1
    private var normalStorkWidth = 0
    private var pressedStorkWidth = 0
    private var selectedStorkWidth = 0
    private var enabledStorkWidth = 0
    //按钮字体颜色
    private var normalTextColor: Int = 0
    private var pressedTextColor: Int = 0
    private var enabledTextColor: Int = 0
    private var selectedTextColor: Int = 0


    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.SuperButton)
            try {
                //按钮弧度
                radius = ta.getDimensionPixelOffset(R.styleable.SuperButton_sb_radius, radius)
                if (radius == 0) {
                    topLeftRadius = ta.getDimensionPixelOffset(
                        R.styleable.SuperButton_sb_topLeftRadius,
                        topLeftRadius
                    )
                    topRightRadius = ta.getDimensionPixelOffset(
                        R.styleable.SuperButton_sb_topRightRadius,
                        topRightRadius
                    )
                    bottomLeftRadius = ta.getDimensionPixelOffset(
                        R.styleable.SuperButton_sb_bottomLeftRadius,
                        bottomLeftRadius
                    )
                    bottomRightRadius = ta.getDimensionPixelOffset(
                        R.styleable.SuperButton_sb_bottomRightRadius,
                        bottomRightRadius
                    )
                } else {
                    topLeftRadius = radius
                    topRightRadius = radius
                    bottomLeftRadius = radius
                    bottomRightRadius = radius
                }
                //按钮背景
                normalBackground =
                    ta.getColor(R.styleable.SuperButton_sb_normalBackground, normalBackground)
                pressedBackground =
                    ta.getColor(R.styleable.SuperButton_sb_pressedBackground, pressedBackground)
                selectedBackground =
                    ta.getColor(R.styleable.SuperButton_sb_selectedBackground, selectedBackground)
                enabledBackground =
                    ta.getColor(R.styleable.SuperButton_sb_enabledBackground, enabledBackground)
                //按钮背景样式
                backgroundStyle =
                    ta.getInteger(R.styleable.SuperButton_sb_backgroundStyle, backgroundStyle)
                normalBackgroundStyle = ta.getInteger(
                    R.styleable.SuperButton_sb_normalBackgroundStyle,
                    normalBackgroundStyle
                )
                pressedBackgroundStyle = ta.getInteger(
                    R.styleable.SuperButton_sb_pressedBackgroundStyle,
                    pressedBackgroundStyle
                )
                selectedBackgroundStyle = ta.getInteger(
                    R.styleable.SuperButton_sb_selectedBackgroundStyle,
                    selectedBackgroundStyle
                )
                enabledBackgroundStyle = ta.getInteger(
                    R.styleable.SuperButton_sb_enabledBackgroundStyle,
                    enabledBackgroundStyle
                )
                //边框宽度
                storkWidth =
                    ta.getDimensionPixelOffset(R.styleable.SuperButton_sb_storkWidth, storkWidth)
                normalStorkWidth = ta.getDimensionPixelOffset(
                    R.styleable.SuperButton_sb_normalStorkWidth,
                    storkWidth
                )
                pressedStorkWidth = ta.getDimensionPixelOffset(
                    R.styleable.SuperButton_sb_pressedStorkWidth,
                    storkWidth
                )
                selectedStorkWidth = ta.getDimensionPixelOffset(
                    R.styleable.SuperButton_sb_selectStorkWidth,
                    storkWidth
                )
                enabledStorkWidth = ta.getDimensionPixelOffset(
                    R.styleable.SuperButton_sb_enabledStorkWidth,
                    storkWidth
                )
                //按钮字体颜色
                normalTextColor =
                    ta.getColor(R.styleable.SuperButton_sb_normalTextColor, normalTextColor)
                pressedTextColor =
                    ta.getColor(R.styleable.SuperButton_sb_pressedTextColor, pressedTextColor)
                selectedTextColor =
                    ta.getColor(R.styleable.SuperButton_sb_selectedTextColor, selectedTextColor)
                enabledTextColor =
                    ta.getColor(R.styleable.SuperButton_sb_enabledTextColor, enabledTextColor)
            } finally {
                ta.recycle()
            }
        }
        initView()
    }

    private fun initView() {
        gravity = Gravity.CENTER
        //设置背景颜色
        initBackground()
        //设置字体颜色
        initTextColor()
    }

    private fun initBackground() {
        val outRect: FloatArray = floatArrayOf(
            topLeftRadius.toFloat(),
            topLeftRadius.toFloat(),
            topRightRadius.toFloat(),
            topRightRadius.toFloat(),
            bottomRightRadius.toFloat(),
            bottomRightRadius.toFloat(),
            bottomLeftRadius.toFloat(),
            bottomLeftRadius.toFloat()
        )
        val drawable: StateListDrawable = StateListDrawable()

        //按压颜色
        if (pressedBackground != 0) {
            Log.d("TAG", "设置按压颜色${pressedBackground}")
            val pressedDrawable = buildGradientDrawable(
                outRect,
                pressedBackgroundStyle,
                backgroundStyle,
                pressedBackground,
                pressedStorkWidth
            )
            drawable.addState(pressedState, pressedDrawable)
        }
        //选中颜色
        if (selectedBackground != 0) {
            val selectedDrawable = buildGradientDrawable(
                outRect,
                selectedBackgroundStyle,
                backgroundStyle,
                selectedBackground,
                selectedStorkWidth
            )
            drawable.addState(selectedState, selectedDrawable)
        }
        //不可点击颜色
        if (enabledBackground != 0) {
            Log.d("TAG", "设置不可点击颜色${enabledBackground}")
            val enabledDrawable = buildGradientDrawable(
                outRect,
                enabledBackgroundStyle,
                backgroundStyle,
                enabledBackground,
                enabledStorkWidth
            )
            drawable.addState(enabledState, enabledDrawable)
        }

        //默认颜色
        if (normalBackground != 0) {
            Log.d("TAG", "设置默认颜色")
            val normalDrawable = buildGradientDrawable(
                outRect,
                normalBackgroundStyle,
                backgroundStyle,
                normalBackground,
                normalStorkWidth
            )
            drawable.addState(normalState, normalDrawable)
        }
        if (drawable.stateCount > 0) {
            background = drawable
        }
    }

    private fun buildGradientDrawable(
        outRect: FloatArray,
        backgroundStyle: Int,
        defaultBackgroundStyle: Int,
        backgoundColor: Int,
        storkWidth: Int
    ): GradientDrawable {
        val drawable = GradientDrawable()
        drawable.cornerRadii = outRect
        when (backgroundStyle) {
            1 -> drawable.setColor(backgoundColor)
            2 -> drawable.setStroke(storkWidth, backgoundColor)
            0 -> {
                when (defaultBackgroundStyle) {
                    1 -> drawable.setColor(backgoundColor)
                    2 -> drawable.setStroke(storkWidth, backgoundColor)
                }
            }
        }
        return drawable
    }

    private fun initTextColor() {
        val states: MutableList<IntArray> = mutableListOf()
        val colors: MutableList<Int> = mutableListOf()
        if (pressedTextColor != 0) {
            colors.add(pressedTextColor)
            states.add(pressedState)
        }
        if (selectedTextColor != 0) {
            colors.add(selectedTextColor)
            states.add(selectedState)
        }
        if (enabledTextColor != 0) {
            colors.add(enabledTextColor)
            states.add(enabledState)
        }
        if (normalTextColor != 0) {
            colors.add(normalTextColor)
            states.add(normalState)
        }
        if (colors.size > 0) {
            val stateArray = states.toTypedArray()
            val colorArray = colors.toIntArray()
            val colorStateList = ColorStateList(stateArray, colorArray)
            setTextColor(colorStateList)
        }
    }

}