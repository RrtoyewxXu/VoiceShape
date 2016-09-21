package com.rrtoyewx.voiceshapelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rrtoyewx on 16/9/21.
 */
public class VoiceShapeView extends View {
    public static final int TOTAL_RECTANGLE_COUNTS = 30;
    private Paint mPaint;

    private int mTotalWidth;
    private int mUnitWidth;

    private Deque<ShapeItem> mAddItemList;
    private List<ShapeItem> mDrawItemList;

    public VoiceShapeView(Context context) {
        this(context, null);
    }

    public VoiceShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoiceShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mAddItemList = new LinkedList<>();
        mDrawItemList = new LinkedList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTotalWidth = MeasureSpec.getSize(widthMeasureSpec);
        calculateWidth();
    }

    private void calculateWidth() {
        mUnitWidth = mTotalWidth / TOTAL_RECTANGLE_COUNTS;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawRunningItem(canvas);
        getItemFromWaiting(canvas);
        if (mDrawItemList.size() > 0) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    postInvalidate();
                }
            },30);
        }
    }

    private void drawRunningItem(Canvas canvas) {
        Iterator<ShapeItem> iterator = mDrawItemList.iterator();
        while (iterator.hasNext()) {
            ShapeItem item = iterator.next();
            if (!item.isOut()) {
                item.draw(canvas, mPaint);
            } else {
                iterator.remove();
            }
        }
    }

    private void getItemFromWaiting(Canvas canvas) {
        ShapeItem addShapeItem = mAddItemList.pollFirst();
        if (addShapeItem != null && mDrawItemList.size() < TOTAL_RECTANGLE_COUNTS) {
            addShapeItem.setWidth(mUnitWidth);
            addShapeItem.setIndex(TOTAL_RECTANGLE_COUNTS);
            addShapeItem.setStartPosition(getWidth() - getPaddingRight(), getHeight(), getPaddingLeft(), getHeight());
            addShapeItem.draw(canvas, mPaint);
            mDrawItemList.add(addShapeItem);
        } else {
            mAddItemList.offerFirst(addShapeItem);
        }
    }

    public void addItem(ShapeItem shapeItem) {
        mAddItemList.offerFirst(shapeItem);
        if (mDrawItemList.size() == 0) {
            postInvalidate();
        }
    }

}
