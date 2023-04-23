package com.e.algorithmvisualiser.algorithm.sorting.logs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

public class PaintView extends View {

    ArrayList<Integer> array;
    Paint paint;
    Paint highlightPaintSwap;
    Paint highlightPaintTrace;
    Paint textPaint;

    int highlightPositionOne = -1, highlightPositionTwo = -1;
    int highlightPosition = -1;
    int lineStrokeWidth = getDimensionInPixel(20);

    public PaintView(Context context, ArrayList<Integer> arrayList) {
        super(context);
        initialise();
        this.array = arrayList;
    }

    public void initialise()
    {
        paint = new Paint();
        paint.setColor(Color.parseColor("#282828"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(lineStrokeWidth);

        highlightPaintSwap = new Paint(paint);
        highlightPaintSwap.setColor(Color.parseColor("#F88013"));

        highlightPaintTrace = new Paint(paint);
        highlightPaintTrace.setColor(Color.BLUE);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(getDimensionInPixelFromSP(15));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array != null) {

            float maxi= Integer.MIN_VALUE;
            for(int i=0;i<array.size();i++)
            {
                if(maxi<array.get(i))
                {
                    maxi = array.get(i);
                }
            }
            int numberOfLines = array.size();

            float margin = (float)(getWidth() - (30 * numberOfLines)) / (numberOfLines + 1);

            float xPos = margin + getDimensionInPixel(10);
            for (int i = 0; i < array.size(); i++) {

                if (i == highlightPositionOne || i == highlightPositionTwo) {
                    canvas.drawLine(xPos, (getHeight()-50) - (float) ((array.get(i) / maxi) * (getHeight()-50))+20, xPos, getHeight()-50, highlightPaintSwap);
                } else if (i == highlightPosition)
                    canvas.drawLine(xPos, (getHeight()-50) - (float) ((array.get(i) / maxi) * (getHeight()-50))+20, xPos, getHeight()-50, highlightPaintTrace);
                else {
                    canvas.drawLine(xPos, (getHeight()-50) - (float) ((array.get(i) / maxi) * (getHeight()-50))+20, xPos, getHeight()-50, paint);
                }

                canvas.drawText(String.valueOf(array.get(i)), xPos-(float)lineStrokeWidth/2, getHeight()-5, textPaint);

                xPos += margin + 30;
            }
            highlightPositionOne = -1;
            highlightPositionTwo = -1;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(200));
    }


    public void highlightSwap(int one, int two) {
        this.highlightPositionOne = one;
        this.highlightPositionTwo = two;
        this.highlightPosition = -1;
        invalidate();
    }

    public void highlightTrace(int position) {
        this.highlightPosition = position;
        this.highlightPositionOne = -1;
        this.highlightPositionTwo = -1;
        invalidate();
    }

    public void onCompleted() {
        this.highlightPosition = -1;
        this.highlightPositionTwo = -1;
        this.highlightPositionOne = -1;
        invalidate();
    }

    public int getDimensionInPixel(int dp) {
        int density = getResources().getDisplayMetrics().densityDpi;

        int modifieddp = dp;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                modifieddp = dp - dp / 2;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                modifieddp = dp - dp / 3;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                modifieddp = dp - dp / 4;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
            case DisplayMetrics.DENSITY_XXHIGH:
            case DisplayMetrics.DENSITY_XXXHIGH:
                modifieddp = dp;
                break;
            default:
                break;
        }

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, modifieddp, getResources().getDisplayMetrics());
    }

    public int getDimensionInPixelFromSP(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public void resetCanvas()
    {
        array.clear();
        invalidate();
    }
}
