package com.hz.zxk.superview_kotlin.layout.helper

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.hz.zxk.superview_kotlin.R

/**
@author zhengxiaoke
@date 2020/4/22 12:01 PM
 */
class RoundLayoutHelper(
    context: Context,
    view: View,
    attrs: AttributeSet? = null
) {
    var radius: Int = 0
    var topLeftRadius: Int = 0
    var topRightRadius: Int = 0
    var bottomLeftRadius: Int = 0
    var bottomRightRadius: Int = 0
    private var width: Int = 0
    private var height: Int = 0

    private var paint: Paint
    private var clipPath: Path
    private var radii = FloatArray(8)


    init {
        attrs?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.RoundAttrs)
            try {
                radius = ta.getDimensionPixelOffset(R.styleable.RoundAttrs_round_radius, radius)
                if (radius <= 0) {
                    topLeftRadius = ta.getDimensionPixelOffset(
                        R.styleable.RoundAttrs_round_topLeftRadius,
                        topLeftRadius
                    )
                    topRightRadius = ta.getDimensionPixelOffset(
                        R.styleable.RoundAttrs_round_topRightRadius,
                        topRightRadius
                    )
                    bottomLeftRadius =
                        ta.getDimensionPixelOffset(
                            R.styleable.RoundAttrs_round_bottomLeftRadius,
                            bottomLeftRadius
                        )
                    bottomRightRadius =
                        ta.getDimensionPixelOffset(
                            R.styleable.RoundAttrs_round_bottomRightRadius,
                            topRightRadius
                        )
                } else {
                    topLeftRadius = radius
                    topRightRadius = radius
                    bottomLeftRadius = radius
                    bottomRightRadius = radius
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

        view.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.WHITE
        clipPath = Path()
    }


    fun onSizeChange(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    fun draw(canvas: Canvas?) {

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val roundCanvas = Canvas(bitmap)
        Log.d("TAG", "radius=${radii[6]}")
        clipPath.addRoundRect(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            radii,
            Path.Direction.CW
        )
        roundCanvas.drawPath(clipPath, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas!!.drawBitmap(bitmap, 0f, 0f, paint)
    }
}