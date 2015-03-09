package com.test.huangxingli.testlayouttransaction;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by huangxingli on 2015/3/9.
 */
public class FixedGridLayout extends ViewGroup {
    int cellWidth,cellHeight;

    public FixedGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedGridLayout(Context context) {
        super(context);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mCellWith=cellWidth;
        int mCellHeight=cellHeight;
        int childCount=getChildCount();
        int x=0;
        int y=0;
        int index=0;
        int columns=(r-l)/mCellWith;
        if (columns<0){
            columns=1;
        }

        for (int i=0;i<childCount;i++){
            View child=getChildAt(i);
            String content=((Button)child).getText().toString();
          //  Log.v("TAG","=====CONTENT IS ===="+content);
            int w=child.getMeasuredWidth();
            int h=child.getMeasuredHeight();

            int cl=x+(mCellWith-w)/2;
            int ch=y+(mCellHeight-h)/2;

            child.layout(cl,ch,cl+w,ch+h);
            if (index>=columns-1){
                index=0;
                x=0;
                y +=cellHeight;
                Log.v("TAG","==========y is "+y);
            }else{
                index++;
                x +=cellWidth;
                Log.v("TAG","======x is "+x);

            }

}

}
    public void setCellWidth(int width){
        this.cellWidth=width;
        requestLayout();

    }
    public void setCellHeight(int height){
        this.cellHeight=height;
        requestLayout();
    }
    public int getCellWidth(){
        return cellWidth;
    }
    public int getCellHeight(){
        return cellHeight;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childWidthSpec=MeasureSpec.makeMeasureSpec(cellWidth,MeasureSpec.AT_MOST);
        int childHeightSpec=MeasureSpec.makeMeasureSpec(cellHeight,MeasureSpec.AT_MOST);

        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            child.measure(childWidthSpec,childHeightSpec);
        }
        int minCout=childCount>3?childCount:3;
        setMeasuredDimension(resolveSize(minCout*cellWidth,widthMeasureSpec),resolveSize(cellHeight*minCout,heightMeasureSpec));
    }

}
