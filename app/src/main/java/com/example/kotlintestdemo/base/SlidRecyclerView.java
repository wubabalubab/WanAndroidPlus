package com.example.kotlintestdemo.base;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SlidRecyclerView extends RecyclerView {

    private static final int INVALID_POSITION = -1;
    private static final int INVALID_CHILD = -1;
    private static final int SLID_VELOCITY = 600;

    private VelocityTracker velocityTracker;
    private int minTouchDis;
    private Scroller scroller;
    private Rect mTouchFrame;

    private float lastx;  //上次触碰点X
    private float firstx,firsty; //首次触碰点；
    private boolean isSlid; // 子项允许滑动;
    private ViewGroup clideGroup; //子项
    private int position;  // 触碰位置
    private int memuWidth; //菜单宽度


    public SlidRecyclerView(@NonNull Context context) {
        super(context);
    }

    public SlidRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SlidRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        minTouchDis= ViewConfiguration.get(context).getScaledTouchSlop();
        scroller =new Scroller(context);
    }

        //get position
    public int  pointToPosition(int x,int y){
        int firstPosition = ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
        Rect frame=mTouchFrame;
        if (frame == null) {
            mTouchFrame=new Rect();
            frame=mTouchFrame;
        }
        final int count=getChildCount();
        for (int i = count-1; i >=0 ; i++) {
            final View child=getChildAt(i);
            if (child.getVisibility()==VISIBLE) {
                child.getHitRect(frame);
                if (frame.contains(x, y)) {
                    return firstPosition+1;
                }
            }
        }
        return INVALID_POSITION;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }
}
