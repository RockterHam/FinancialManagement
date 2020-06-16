package com.example.financialmanagement.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.financialmanagement.R;

public  class MyHorizontalStatisticsView extends View {
    private Context mContext;
    /** 背景画笔 **/
    private Paint mBgPaint;

    /** 画笔 **/
    private Paint mPaint;
    private Paint mTextPaint;

    private int mMaxWidth;
    private int mMaxHeight;

    private int selectColor = 0;
    private int value = 30;
    private int height = 20;

    /** 文字盖度 **/
    private int mTextW;
    /** 文字宽度 **/
    private int mTextH;

    public MyHorizontalStatisticsView(Context context) {
        super(context,null);
        mContext = context;
    }

    public MyHorizontalStatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        mContext = context;
        TypedArray ta = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.MyStatisticsView, 0, 0);
        if (ta!=null){
            selectColor = ta.getColor(R.styleable.MyStatisticsView_select_color,0);
            value = ta.getInt(R.styleable.MyStatisticsView_value,0);
        }
        ta.recycle();
        init();
    }

    public MyHorizontalStatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray ta = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.MyStatisticsView, defStyleAttr, 0);
        if (ta!=null){
            selectColor = ta.getColor(R.styleable.MyStatisticsView_select_color,0);
            value = ta.getInt(R.styleable.MyStatisticsView_value,0);
        }
        ta.recycle();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMaxWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMaxHeight = mTextH+80;

        setMeasuredDimension(mMaxWidth,mMaxHeight);
    }

    private void init(){
        mBgPaint = new Paint();
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setAntiAlias(true);
        //背景颜色（自己定义）
        mBgPaint.setColor(ContextCompat.getColor(mContext, R.color.alipay));

        mPaint = new Paint();
        mPaint.setColor(selectColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        //后面数值颜色（自己定义）
        mTextPaint.setColor(ContextCompat.getColor(mContext, R.color.wechat));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(sp2px(mContext,12));

        mTextW = getTextWidth(value+"%",mTextPaint);
        mTextH = getTexHeight(value+"%",mTextPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        deawBG(canvas);
        deawValue(canvas);
    }

    /**
     * 画背景
     */
    private void deawBG(Canvas canvas){
        if (mBgPaint==null){
            return;
        }
        RectF rectF = new RectF();
        rectF.top=(mMaxHeight-height)/2;
        rectF.left=0;
        rectF.bottom=(mMaxHeight-height)/2+height;
        rectF.right=mMaxWidth - 2*mTextW;
        canvas.drawRoundRect(rectF,height/2,height/2,mBgPaint);
    }

    /**
     * 画值
     */
    private void deawValue(Canvas canvas){
        if (mPaint==null){
            return;
        }

        RectF rectF = new RectF();
        rectF.left=0;
        rectF.top=(mMaxHeight-height)/2;
        rectF.bottom=(mMaxHeight-height)/2+height;
        rectF.right=(mMaxWidth - 2*mTextW)/100 * value;
        canvas.drawRoundRect(rectF,height/2,height/2,mPaint);

        canvas.drawText(value+"%",mMaxWidth - mTextW-mTextW/2,(mMaxHeight-mTextH)/2+mTextH-6,mTextPaint);
    }

    /**
     * 获取文字宽度
     * @param text
     * @param paint
     * @return
     */
    private int getTextWidth(String text, Paint paint) {
        // 文字所在区域的矩形
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }

    /**
     *   获取文字高度
     */
    private int getTexHeight(String text, Paint paint) {
        // 文字所在区域的矩形
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     */
    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public void setData(int value){
        this.value =value;
        invalidate();
    }
    public void setColor(int color){ this.selectColor = color; invalidate(); }
}