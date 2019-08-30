package com.chivan.stroketextdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author Chivan
 * @description:
 * @date : 2019-08-22 17:50
 */
public class StrokeTextView extends AppCompatTextView {
    private AppCompatTextView textView;

    public StrokeTextView(Context context) {
        this(context, null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textView = new AppCompatTextView(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        TextPaint paint = textView.getPaint();
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
        textView.setTextColor(Color.GRAY);
        textView.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        textView.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        CharSequence text = textView.getText();
        if (text == null || !text.equals(this.getText())) {
            textView.setText(getText());
            postInvalidate();
        }
        textView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        textView.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        textView.draw(canvas);
        super.onDraw(canvas);
    }
}
