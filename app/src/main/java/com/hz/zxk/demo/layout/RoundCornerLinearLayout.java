package com.hz.zxk.demo.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

public class RoundCornerLinearLayout extends LinearLayout {
    private static final String TAG = "RoundCornerLinearLayout";

    public RoundCornerLinearLayout(Context context) {
        super(context);
    }

    public RoundCornerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setWillNotDraw(false);
    }

    public RoundCornerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas) {
        Log.e(TAG, "draw");
        super.draw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);//颜色可以任意值。
        setLayerType(LAYER_TYPE_HARDWARE, null);
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap);
//        canvas1.drawRoundRect(0, 0, getWidth(), getHeight(), 60, 60, paint);
        Path clipPath = new Path();
        float[] radii = {0, 0, 60, 60, 0, 0, 60, 60};
        clipPath.addRoundRect(0, 0, getWidth(), getHeight(), radii, Path.Direction.CW);
        canvas1.drawPath(clipPath,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}