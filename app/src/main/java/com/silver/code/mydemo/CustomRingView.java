package com.silver.code.mydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shichengxiang on 2015/5/13.
 */
public class CustomRingView extends View {

    private float angle = 0;
    private float angle2 = 0;
    int flag = 0;
    float ringWidth = 6,ringWidth_red=15,ringWidth_gray=10;//圆环的宽度

    float startAngle = 135;//开始的角度
    float left = 0;
    float top = 0;
    float radius = 200;//半径
    boolean isBack=false;

    public float getBackAngle() {
        return backAngle;
    }

    public void setBackAngle(float backAngle) {
        this.backAngle = backAngle;
    }

    public float getSweepAngle1() {
        return sweepAngle1;
    }

    private float backAngle=-1;//默认初始值为-1把....

    public void setSweepAngle1(float sweepAngle1) {
        this.sweepAngle1 = sweepAngle1;
    }

    public float getSweepAngle2() {
        return sweepAngle2;
    }

    public void setSweepAngle2(float sweepAngle2) {
        this.sweepAngle2 = sweepAngle2;
    }

    private float sweepAngle1 = 135;
    private float sweepAngle2 = 200;

    //test
    int num = 0;

    float redundanceX = 15;
    float redundanceY = 20;

    public CustomRingView(Context context) {
        super(context);
    }

    public CustomRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 注销 实现动态效果
         */
        if(backAngle==-1){
            angle=sweepAngle1;
        }else{
            angle=backAngle;
        }
        angle2=sweepAngle2;

        double radians = Math.toRadians(ringWidth / (4 * (radius - ringWidth / 2)));
        //double degree=  Math.asin(radians);
        //double radians = Math.toRadians(degree)*2;
        ringWidth = 16;//yellow宽度
        Paint paint = new Paint();
        paint.setAntiAlias(true);//消除锯齿
        paint.setStyle(Paint.Style.STROKE);

        //3灰色图:
        RectF rect3 = new RectF(0 + ringWidth + ringWidth_gray + redundanceX, 0 + ringWidth + ringWidth_gray + redundanceY, (radius + redundanceX) * 2 - ringWidth - ringWidth_gray - redundanceX, (radius + redundanceY) * 2 - ringWidth - ringWidth_gray - redundanceY);
        paint.setColor(Color.parseColor("#a1a1a1"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ringWidth_gray);// 宽度
        canvas.drawArc(rect3, startAngle, 270+5, false, paint);
        //3.1实心圆
        //1.1实心小圆
        paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(0);
        float x = (float) radius + (float) Math.cos(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
        float y = (float) radius + (float) Math.sin(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
        canvas.drawCircle(x, y, ringWidth_gray/2, paint);
        //1.2小圆
        x = (float) radius + (float) Math.cos(Math.toRadians(startAngle + 270+5) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
        y = (float) radius + (float) Math.sin(Math.toRadians(startAngle + 270+5) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
        canvas.drawCircle(x, y, ringWidth_gray/2, paint);
        //canvas.drawCircle(x, y, ringWidth_gray/2, paint);

        paint.setColor(Color.parseColor("#ffad01"));
        paint.setStrokeWidth(ringWidth);


        //1.绘制黄色圆环
        //判断今日与历史的大小
        paint.setStyle(Paint.Style.STROKE);
        RectF rect1 = new RectF(0 + ringWidth / 2 + redundanceX, 0 + ringWidth / 2 + redundanceY, (radius + redundanceX) * 2 - ringWidth / 2 - redundanceX, (radius + redundanceY) * 2 - ringWidth / 2 - redundanceY);
        canvas.drawArc(rect1, startAngle, angle - (float) radians, false, paint);
        //1.1实心小圆
        paint.setStyle(Paint.Style.FILL);
        x = (float) radius + (float) Math.cos(Math.toRadians(startAngle) - radians) * (radius - ringWidth / 2) + redundanceX;
        y = (float) radius + (float) Math.sin(Math.toRadians(startAngle) - radians) * (radius - ringWidth / 2) + redundanceY;
        canvas.drawCircle(x, y, ringWidth / 2, paint);
        //1.2空心小圆
        x = (float) radius + (float) Math.cos(Math.toRadians(startAngle + angle) - radians) * (radius - ringWidth / 2) + redundanceX;
        y = (float) radius + (float) Math.sin(Math.toRadians(startAngle + angle) - radians) * (radius - ringWidth / 2) + redundanceY;
        canvas.drawCircle(x, y, ringWidth / 2 + ringWidth / 3, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, ringWidth / 3 + ringWidth / 5, paint);


        //绘制红色
        RectF rect2 = new RectF(0 + ringWidth + ringWidth_red/2 + redundanceX, 0 + ringWidth + ringWidth_red/2 + redundanceY, (radius + redundanceX) * 2 - ringWidth - ringWidth_red/2 - redundanceX, (radius + redundanceY) * 2 - ringWidth - ringWidth_red/2 - redundanceY);
        //ringWidth = ringWidth_red;
        paint.setColor(Color.parseColor("#cc1b1c"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ringWidth_red);//red 宽度
        canvas.drawArc(rect2, startAngle, angle2 - (float) radians, false, paint);

        /*
        * 固定图片不动态*/

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#cc1b1c"));//红色
        //paint.setStrokeWidth(0);
//            x = (float) radius + (float) Math.cos(Math.toRadians(startAngle) - radians) * (radius - ringWidth / 2) + redundanceX;
        x = (float) radius + (float) Math.cos(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
        y = (float) radius + (float) Math.sin(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
        canvas.drawCircle(x, y, ringWidth_red / 2, paint);
        //1.2空心小圆
        x = (float) radius + (float) Math.cos(Math.toRadians(startAngle + angle2) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
        y = (float) radius + (float) Math.sin(Math.toRadians(startAngle + angle2) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
        canvas.drawCircle(x, y, ringWidth_red / 2+ringWidth_red/2, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, ringWidth_red / 3 + ringWidth_red / 3, paint);
/**
 * 可实现动态效果
 */

//        if (angle < sweepAngle1) {
//            if(!isBack){
//                angle+=2;
//                invalidate();
//            }
//        }
//        if (angle >= sweepAngle1 && angle2 < sweepAngle2 && !isBack) {
//            //1.1实心小圆
//            paint.setStyle(Paint.Style.FILL);
//            paint.setColor(Color.parseColor("#cc1b1c"));//红色
//            //paint.setStrokeWidth(0);
////            x = (float) radius + (float) Math.cos(Math.toRadians(startAngle) - radians) * (radius - ringWidth / 2) + redundanceX;
//            x = (float) radius + (float) Math.cos(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
//            y = (float) radius + (float) Math.sin(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
//            canvas.drawCircle(x, y, ringWidth_red / 2, paint);
//            //1.2空心小圆
//            x = (float) radius + (float) Math.cos(Math.toRadians(startAngle + angle2) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
//            y = (float) radius + (float) Math.sin(Math.toRadians(startAngle + angle2) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
//            canvas.drawCircle(x, y, ringWidth_red / 2+ringWidth_red/2, paint);
//            paint.setColor(Color.WHITE);
//            canvas.drawCircle(x, y, ringWidth_red / 3 + ringWidth_red / 3, paint);
//            angle2 += 4;
//            invalidate();
//        }
//        if(angle2>=sweepAngle2){
//            isBack=true;
//        }
//        if (angle2 != 0) {
//            //ringWidth = ringWidth_red;
//            //1.1实心小圆
//            paint.setStyle(Paint.Style.FILL);
//            paint.setColor(Color.parseColor("#cc1b1c"));//红色
//            //paint.setStrokeWidth(0);
//            x = (float) radius + (float) Math.cos(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
//            y = (float) radius + (float) Math.sin(Math.toRadians(startAngle)) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
//            canvas.drawCircle(x, y, ringWidth_red / 2, paint);
//            //1.2空心小圆
//            x = (float) radius + (float) Math.cos(Math.toRadians(startAngle + angle2) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceX;
//            y = (float) radius + (float) Math.sin(Math.toRadians(startAngle + angle2) - radians) * (radius - ringWidth - ringWidth_red/2) + redundanceY;
//            canvas.drawCircle(x, y, ringWidth_red / 2 + ringWidth_red / 2, paint);
//            paint.setColor(Color.WHITE);
//            canvas.drawCircle(x, y, ringWidth_red / 3 + ringWidth_red / 3, paint);
//        }
//        //黄线返回
//        if(backAngle<angle && backAngle!=-1 &&isBack){
//            angle-=2;
//            invalidate();
//        }





        /*//获取最大宽度的ringwidth
        for(DoughnutSeries series:serieses){
            int width=series.getStyle();
            if(ringWidth<width)
                ringWidth=width;
        }
        *//**
         * 绘制集合中的环
         *//*
        for (DoughnutSeries series : serieses) {
            paint.setStyle(Paint.Style.STROKE);
            int color = series.getColor();
            sweepAngle = (float) series.getPersent() * 3.6f;
            String name = series.getName();
            paint.setColor(color);
            int style=series.getStyle();
            paint.setStrokeWidth(style);
            RectF rect = new RectF(0 + ringWidth / 2, 0 + ringWidth / 2, left * 2 - ringWidth / 2, top * 2 - ringWidth / 2);
            canvas.drawArc(rect, startAngle, sweepAngle, false, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(0);
            //小原点设计
            float x=(float)left+(float)Math.cos(Math.toRadians(startAngle+sweepAngle))*(left-ringWidth/2);
            float y=(float)top+(float)Math.sin(Math.toRadians(startAngle+sweepAngle))*(top-ringWidth/2);
            canvas.drawCircle(x,y,style/2,paint);
            startAngle += sweepAngle;
        }
        for(int i=0;i<serieses.size();i++){
            DoughnutSeries series=serieses.get(i);
            if(i==0){//绘全圆

            }else if(i==1){//会半圆,或全园

            }else{//百分比绘圆

            }
        }
/*
        *//**//**
         * 绘制文字
         *//*
        paint.setStrokeWidth(1);
        Typeface tf = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        paint.setTextSize(20);
        paint.setTypeface(tf);
        canvas.drawText("目前销售额\r\n432958.00万", radius + redundanceX, radius + redundanceY
                , paint);*/
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //setLayoutParams();
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int specModeW=MeasureSpec.getMode(measuredWidth);
//        int specModeH=MeasureSpec.getMode(measuredHeight);
        int width=(int)(redundanceX+radius)*2;
        int height=(int)(radius*2-radius*(1-Math.cos(Math.PI/4))+redundanceY);
        setMeasuredDimension(width, height);

    }



    public void initialize(float left, float top, float radius) {
        this.redundanceX = left;
        this.redundanceY = top;
        this.radius = radius;
    }

    public void reSet() {

    }

}
