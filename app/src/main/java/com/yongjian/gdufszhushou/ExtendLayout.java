package com.yongjian.gdufszhushou;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by YONGJIAN on 2016/4/29 0029.
 */
public class ExtendLayout extends DrawerLayout {
    public ExtendLayout(Context context) {
        super(context);
    }

    public ExtendLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            Log.e("CCC","拦截了");
            return false;
        }
    }
}
