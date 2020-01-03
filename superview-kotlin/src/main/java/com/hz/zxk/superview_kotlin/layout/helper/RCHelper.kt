package com.hz.zxk.superview_kotlin.layout.helper

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.hz.zxk.superview_kotlin.R


class RCHelper {
    val radii: FloatArray = FloatArray(8)
    lateinit var mPaint: Paint
    lateinit var mPath: Path
    lateinit var mLayer: RectF
    lateinit var mAreaRegion: Region

    var topLeftRadius = 0
    var topRightRadius = 0
    var bottomRightRadius = 0
    var bottomLeftRadius = 0
    var clipBackground = true


    fun initAttrs(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RCAttrs)
            try {
                val radius = ta.getDimensionPixelOffset(R.styleable.RCAttrs_radius, 0)
                if (radius != 0) {
                    topLeftRadius = radius
                    topRightRadius = radius
                    bottomRightRadius = radius
                    bottomLeftRadius = radius
                } else {
                    topLeftRadius =
                        ta.getDimensionPixelOffset(R.styleable.RCAttrs_topLeftRadius, 0)
                    topRightRadius =
                        ta.getDimensionPixelOffset(R.styleable.RCAttrs_topRightRadius, 0)
                    bottomRightRadius = ta.getDimensionPixelOffset(
                        R.styleable.RCAttrs_bottomRightRadius,
                        0
                    )
                    bottomLeftRadius = ta.getDimensionPixelOffset(
                        R.styleable.RCAttrs_bottomLeftRadius,
                        0
                    )
                }
            } finally {
                ta.recycle()
            }
        }

        radii[0] = topLeftRadius.toFloat()
        radii[1] = topLeftRadius.toFloat()
        radii[2] = topRightRadius.toFloat()
        radii[3] = topRightRadius.toFloat()
        radii[4] = bottomRightRadius.toFloat()
        radii[5] = bottomRightRadius.toFloat()
        radii[6] = bottomLeftRadius.toFloat()
        radii[7] = bottomLeftRadius.toFloat()

        mLayer = RectF()
        mPath = Path()
        mAreaRegion = Region()
        mPaint = Paint()
        mPaint.color = Color.WHITE
        mPaint.isAntiAlias = true
    }

}