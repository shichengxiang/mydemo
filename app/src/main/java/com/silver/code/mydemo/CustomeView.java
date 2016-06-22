package com.silver.code.mydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
public class CustomeView extends View {
    private Paint mPaint;
    private float radius =0;
    private int interval=0;//间隔角度
    private float topH=15;
    private float strokeWidth=0;
    private Context mContext;
    int increment=2;
    int startRadius=0;
    private int[] dates;
    private int index0=0,index1=0,index2=0,index3=0;
    private int count0=0,count1=0,count2=0,count3=0;
    public CustomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initDate();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true); // 消除锯齿
    }

    public CustomeView(Context context) {
        super(context);
    }

    public void setDate(int... index){
        this.dates=index;
        this.index0=index[0];
        this.index1=index[1];
        this.index2=index[2];
        this.index3=index[3];
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#CE2F4D"));
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        //灰色圆环
        mPaint.setColor(Color.parseColor("#d3d3d3"));
        RectF rect = new RectF(radius/2+0, topH+0, radius/2+2 * radius, topH+2 * radius);
        canvas.drawArc(rect, 0, 360, false, mPaint);

        mPaint.setColor(Color.parseColor("#f2c278"));
        RectF rect1 = new RectF(radius/2+0, topH+0, radius/2+2 * radius, topH+2 * radius);
        count0+=increment;
        if(count0<index0){
            canvas.drawArc(rect1, 0+interval, count0, false, mPaint);
        }else {
            canvas.drawArc(rect1, 0+interval, index0, false, mPaint);
        }

        mPaint.setColor(Color.parseColor("#f2d97d"));
        RectF rect2 = new RectF(radius/2+0, topH+0, radius/2+2 * radius, topH+2 * radius);
        count1+=increment;
        if(count1<index1){
            canvas.drawArc(rect2, index0+interval, count1, false, mPaint);
        }else {
            canvas.drawArc(rect2, index0+interval, index1, false, mPaint);
        }

        mPaint.setColor(Color.parseColor("#7dc4b4" ));
        RectF rect3 = new RectF(radius/2+0, topH+0, radius/2+2 * radius, topH+2 * radius);
        count2+=increment;
        if(count2<index2){
            canvas.drawArc(rect3, index0+index1+interval, count2, false, mPaint);
        }else{
            canvas.drawArc(rect3, index0+index1+interval, index2, false, mPaint);
        }


        mPaint.setColor(Color.parseColor("#75d4c0"));
        RectF rect4 = new RectF(radius/2+0, topH+0, radius/2+2 * radius, topH+2 * radius);

        count3+=increment;
        if(count3<index3){
            canvas.drawArc(rect4, index0+index1+index2+interval, count3, false, mPaint);
        }else {
            canvas.drawArc(rect4, index0+index1+index2+interval, index3, false, mPaint);
        }
        int index=dates[0];
        for(int i=0;i<3;i++){
            if(index<dates[i+1]){
                index=dates[i+1];
            }
        }
        if(startRadius<index){
            postInvalidate();
        }
    }
    private void initDate(){
        float density=mContext.getResources().getDisplayMetrics().density;
        float screentWidth=mContext.getResources().getDisplayMetrics().widthPixels;
        strokeWidth=16*density;
        topH=10*density;
        radius=screentWidth*3/10f;
    }
    public void startAnim(){
        count0=0;
        count1=0;
        count2=0;
        count3=0;
        postInvalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=(int) (radius*2+radius);
        int height=(int) ((radius+topH)*2);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
