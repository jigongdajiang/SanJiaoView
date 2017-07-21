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
    private String mText;//文字
    private int mTextColor;//文字颜色
    private float mTextSize;//文字大小
    private int mBgColor;//控件背景颜色
    private float padding;//文字与直角边的距离

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
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SanJiaoTextView,defStyleAttr,0);
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
        //得到文字的真实宽高
        textLength = tRect.width();
        textHeight = tRect.height();

        if(-1 != angelLength){
            //指定的直角边的长度，根据文字长度和直角表长度自动计算padding
            padding = (float) ((angelLength /2 * Math.sqrt(2) - textLength)/2);
        }else{
            //如果没有指定直角长度，则根据文字长度和padding自动计算直角边长度
            angelLength = 2* ((float) ((textLength+(2*padding)) * 1.0 / Math.sqrt(2)));
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
        //间距xy方向导致的便宜量
        float sqTh = (float) (padding * 1.0 / Math.sqrt(2));
        //文字长度在xy方向上的大小
        float sqtW = (float) (textLength * 1.0 / Math.sqrt(2));
        //文字高度的一半在xy方向上的偏移量
        float offset = (float) (textHeight/2/Math.sqrt(2));
        //基线的起始x坐标:在基线的基础上要左移文字高度在x轴映射的长度
        float startX = angelLength /2 + sqTh - offset*2;
        //基线的起始y坐标:在基线的基础上要下移文字高度在y轴映射的长度
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
