package com.gjg.sanjiaoview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
/*
    使用实例
    <beidoujf.com.sanjiao.SanJiaoTextView
       android:id="@+id/stv"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:layout_centerInParent="true"
       app:stv_text="募集中"
       app:stv_padding="10dp"
       app:stv_text_color="@color/colorAccent"
       app:stv_text_size="12sp"
       app:stv_background="@color/colorPrimaryDark"/>
 */
/**
 * @author gaojigong
 * @version V1.0
 * @Description:
 * @date 17/3/28
 */
public class SanJiaoTextView extends View {
    private String mText;
    private int mTextColor;
    private float mTextSize;
    private int mBgColor;
    private float padding;

    private Paint textPaint;
    private Paint bgPaint;


    private float textLength;//文字的真实长度
    private float textHeight;//文字的真实高度
    private float angelLength;//直角边长度


    public SanJiaoTextView(Context context) {
        this(context,null);
    }

    public SanJiaoTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SanJiaoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.SanJiaoTextView,defStyleAttr,0);
        mText = array.getString(R.styleable.SanJiaoTextView_stv_text);
        if(TextUtils.isEmpty(mText)){
            mText = "Status";
        }
        mTextColor = array.getColor(R.styleable.SanJiaoTextView_stv_text_color,Color.WHITE);
        mTextSize = array.getDimension(R.styleable.SanJiaoTextView_stv_text_size,36);
        mBgColor = array.getColor(R.styleable.SanJiaoTextView_stv_background,Color.RED);
        padding = array.getDimension(R.styleable.SanJiaoTextView_stv_padding,30);
        angelLength = array.getDimension(R.styleable.SanJiaoTextView_stv_angel_length,-1);
        array.recycle();

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(mTextColor);
        textPaint.setTextSize(mTextSize);
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(mBgColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        calculate();
        int w = (int) Math.ceil(angelLength);
        setMeasuredDimension(w,w);
    }

    private void calculate() {
        Rect tRect = new Rect();
        textPaint.getTextBounds(mText,0,mText.length(),tRect);
        textLength = tRect.width();
        textHeight = tRect.height();
        if(-1 != angelLength){
            padding = (float) ((angelLength /2*Math.sqrt(2) - textLength)/2);
        }else{
            angelLength = 2 * ((float) ((textLength+(2*padding)) * 1.0 / Math.sqrt(2)));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAngle(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        Path tPath = new Path();
        float sqTh = (float) (padding * 1.0 / Math.sqrt(2));
        float sqtW = (float) (textLength * 1.0 / Math.sqrt(2));
        float offset = (float) (textHeight/2/Math.sqrt(2));
        float startX = angelLength /2 + sqTh - offset*2;
        float startY = sqTh + offset*2;
        tPath.moveTo(startX,startY);
        tPath.lineTo(startX+sqtW,startY+sqtW);
        tPath.close();
        canvas.drawTextOnPath(mText,tPath,0,0,textPaint);
    }

    private void drawAngle(Canvas canvas) {
        Path sPath = new Path();
        sPath.moveTo(0,0);
        sPath.lineTo(angelLength, angelLength);
        sPath.lineTo(angelLength,0);
        sPath.close();
        canvas.drawPath(sPath,bgPaint);
    }

    public void setText(String text) {
        this.mText = text;
        requestLayout();
        invalidate();
    }

    public void setBgColor(int bgColor) {
        this.mBgColor = getResources().getColor(bgColor);
        bgPaint.setColor(mBgColor);
        invalidate();
    }
}
