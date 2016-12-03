package com.qzsang.scaleviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/17 0017.
 *
 * 长宽按一定比例
 * 以宽为长度模板
 * heightWeight 、  widthWeight  为0均无效
 *
 */

public class ScaleImageView extends ImageView {

    private float heightWeight = 0;//高
    private float widthWeight = 0;//宽
    public ScaleImageView(Context context) {
        super(context);
        init(context,null,0);
    }

    //自定义的时候默认为充满控件，长宽权重如果有一个为0，则默认为等比
    public ScaleImageView(Context context, float widthWeight, float heightWeight) {
        super(context);
        if (widthWeight == 0 && heightWeight == 0) {
            widthWeight = 1;
            heightWeight = 1;
        } else if (widthWeight == 0){
            widthWeight = heightWeight;
        } else if (heightWeight == 0) {
            heightWeight = widthWeight;
        }
        this.widthWeight = widthWeight;
        this.heightWeight = heightWeight;
     //   setScaleType(ScaleType.CENTER_CROP);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
        //init(context,null,0);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs,0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    public void init (Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs == null){
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QzsangScaleView, defStyleAttr, 0);

        widthWeight = a.getFloat(R.styleable.QzsangScaleView_width_weight, 0);

        heightWeight = a.getFloat(R.styleable.QzsangScaleView_height_weight, 0);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (widthWeight != 0 && heightWeight != 0) {
            super.onMeasure(
                    widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec((int)( MeasureSpec.getSize(widthMeasureSpec) * (heightWeight/widthWeight) ), MeasureSpec.EXACTLY));

        } else {
            super.onMeasure(
                    widthMeasureSpec,
                    heightMeasureSpec);

        }

    }
}
