package com.example.dllo.blevel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.dllo.blevel.tool.Tools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 17/4/18.
 */

public class LinearSelfView extends View {

    private HashMap<Double, Double> map;
    private int marginB;//表格x轴与屏幕底部差
    private int marginLeft;//屏幕坐标原点和表格坐标原点x之差
    private int totalValue;//表格的竖向高度
    private int jvalue;//表格纵向间距
    private String xStr;//x刻度
    private String yStr;//y刻度;
    private boolean isShow;//横竖直线是否显示
    private int bHeight;//坐标原点与屏幕坐标原点之间的高度差
    private Context context;
    private int width;
    private int height;
    private ArrayList<Double> xlk;//存放横坐标的集合
    private Point[] mPoints;
    private Paint paint;
    private Point endPoint;
    private Point startPoint;
    private ArrayList<Integer> horizontal;


    public void setView(Context context, int marginB,
                        int marginLeft, int totalValue, int jvalue, String xStr, String yStr,
                        boolean isShow) {

        this.marginB = marginB;
        this.marginLeft = marginLeft;
        this.totalValue = totalValue;
        this.jvalue = jvalue;
        this.xStr = xStr;
        this.yStr = yStr;
        this.isShow = isShow;

    }
    public void setPointMap(HashMap<Double, Double> map) {
        this.map = map;
    }

    public LinearSelfView(Context context) {
        super(context);
        this.context = context;


    }


    public LinearSelfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public LinearSelfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取屏幕宽
        width = getMeasuredWidth();
        //获取屏幕的高
        height = getMeasuredHeight();

    }

    public void setHorizontal(ArrayList<Integer> horizontal) {
        if(horizontal.size() != map.size()) throw new RuntimeException("传入的坐标与横坐标刻度数不符 坐标数:"+ map.size() + "刻度数: "+horizontal.size());
        this.horizontal = horizontal;
        postInvalidate();
    }

    ArrayList<Integer> xList = new ArrayList<>();//保存x坐标;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //onDraw方法 比较靠前执行,所以说传进来的数组有可能是空的
//        if (horizontal == null) return;

        xlk = Tools.getintFormMap(map);//从小到大保存x的坐标；
        Log.d("LinearSelfView", "宝蓝:" + map.size());

//        marginLeft = 50;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);//实心
        paint.setColor(Color.WHITE);
        bHeight = height - marginB;//算出bHeight的值
        int jSize = totalValue / jvalue;//算出y轴总共有的格子数(单元格数);
        for (int i = 0; i < jSize + 1; i++) {
            //画出与x轴平行的几条线(横线)
            canvas.drawLine(marginLeft, bHeight - bHeight / jSize * i, width, bHeight - bHeight / jSize * i, paint);
            //画出y轴的刻度
            canvas.drawText(String.valueOf(jvalue * i), marginLeft / 2, bHeight - bHeight / jSize * i, paint);
        }
        Log.d("LinearSelfView", "xlk.size():" + xlk.size());
//        这个集合中存放传进来的刻度值
        ArrayList<Integer> xList = new ArrayList<>();
        for (int i = 0; i < xlk.size(); i++) {
             xList.add(marginLeft + (width - marginLeft) / xlk.size() * i);
            int a = marginLeft + (width - marginLeft) / xlk.size() * i;
            Log.d("LinearSelfView", "a:" + a);
            float startX = marginLeft + (width - marginLeft) / horizontal.size() * i;
            float stopX = marginLeft + (width - marginLeft) / horizontal.size() * i;//x刻度
            float stopY = bHeight;
            //竖线
            canvas.drawLine(startX, 0, stopX, stopY, paint);
            Log.d("LinearSelfView", "startX:" + startX + " stopX: "+ stopX + "stopY "+stopY);
            canvas.drawText(String.valueOf(horizontal.get(i)), marginLeft + (width - marginLeft) / horizontal.size() * i, bHeight + 10, paint);
        }



















































































        //所有坐标点换算成手机屏幕view的坐标
        mPoints = getPoints(xList);
        //连线
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorLine));
        drawLine(mPoints, canvas, paint);
        //画点
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        for (int j = 0; j < mPoints.length; j++) {
            canvas.drawRect(pointRect(mPoints[j]), paint);
        }
    }

    //画的小方块(坐标点的周围)
    int RECT_SIZE = 3;

    //对点的左右进行运算
    private RectF pointRect(Point Point) {
        return new RectF(Point.x - RECT_SIZE, Point.y - RECT_SIZE, Point.x + RECT_SIZE, Point.y + RECT_SIZE);
    }

    private void drawLine(Point[] mPoints2, Canvas canvas, Paint paint) {
        //利用相邻的俩个点进行连线
        startPoint = new Point();
        endPoint = new Point();
        for (int i = 0; i < mPoints2.length - 1; i++) {
            startPoint = mPoints2[i];
            endPoint = mPoints2[i + 1];
            //利用所有点的横纵坐标进行连线
            canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint);
        }
    }

    //TODO 随着传入的map值的多少不一样 ,需要改变横坐标的个数
    private Point[] getPoints(ArrayList<Integer> xList) {
        //得到点的数组(横纵坐标)
        Point[] points = new Point[xList.size()];
        for (int i = 0; i < points.length; i++) {
            int py = (int) (bHeight - (bHeight * (map.get(xlk.get(i)) / totalValue)));//y的坐标
            points[i] = new Point(xList.get(i), py);
        }
        return points;
    }


}
