package com.rrtoyewx.voiceshapelibrary;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Rrtoyewx on 16/9/21.
 */
public class ShapeItem {
    private int mHeight;
    private int mWidth;

    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;

    private int mCurrentX;
    private int mCurrentY;

    private int mColor;

    private int mIndex;

    private int[] mColors = new int[]{
            Color.RED, Color.GREEN, Color.YELLOW,
    };

    public ShapeItem(int height) {
        this(height, 0);
    }

    public ShapeItem(int height, int width) {
        this.mHeight = height;
        this.mWidth = width;
        this.mColor = mColors[0];
        this.mIndex = 0;
    }

    public void setStartPosition(int startX, int startY, int endX, int endY) {
        this.mStartX = startX;
        this.mStartY = startY;

        this.mCurrentX = startX;
        this.mCurrentY = startY;

        this.mEndX = endX;
        this.mEndY = endY;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public void setIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public final void draw(Canvas canvas, Paint paint) {
        onDraw(canvas, paint);
        afterDraw();
    }

    public void onDraw(Canvas canvas, Paint paint) {
        int savePaintColor = paint.getColor();
        paint.setColor(mColor);
        Log.e("TAG", mCurrentY + "mCurrentY");
        Log.e("TAG", mWidth + "mWidth");
        canvas.drawRect(mCurrentX, mCurrentY - mHeight, mCurrentX + mWidth - 20, mCurrentY, paint);

        paint.setColor(savePaintColor);
    }

    public void afterDraw() {
        mCurrentX -= mWidth;
        mIndex--;

        if (mIndex < VoiceShapeView.TOTAL_RECTANGLE_COUNTS / mColors.length) {
            mColor = mColors[1];
        } else if (mIndex < (VoiceShapeView.TOTAL_RECTANGLE_COUNTS / mColors.length) * 2) {
            mColor = mColors[2];
        }
    }


    public boolean isOut() {
        return mCurrentX < mEndX;
    }
}
