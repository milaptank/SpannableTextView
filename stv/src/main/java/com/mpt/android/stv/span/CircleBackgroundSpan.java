package com.mpt.android.stv.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

public class CircleBackgroundSpan extends ReplacementSpan {

    private int cornerRadius = 8;
    private int backgroundColor = 0;
    private int textColor = 0;

    public CircleBackgroundSpan(int bgColor, int textColor, int cornerRadius) {
        super();
        this.backgroundColor = bgColor;
        this.cornerRadius = cornerRadius;
        this.textColor = textColor;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        paint.setColor(backgroundColor);
        canvas.drawCircle(x,y,cornerRadius, paint);
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}