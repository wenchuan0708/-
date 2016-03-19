package com.yongjian.gdufszhushou.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yongjian.gdufszhushou.Model.Course;
import com.yongjian.gdufszhushou.R;

import java.util.List;

/**
 * Created by YONGJIAN on 2016/3/19 0019.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<List<Course>> data;
    @Override
    public int getCount() {
        return data.size();
    }

    public ViewPagerAdapter(Context context,List<List<Course>> data) {
        this.context =context;
        this.data = data;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "星期一";
        }else if (position == 1) {
            return "星期二";
        }else if (position == 2) {
            return "星期三";
        }else if (position == 3) {
            return "星期四";
        }else if (position == 4) {
            return "星期五";
        }else if (position == 5) {
            return "星期六";
        }else {
            return "星期天";
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (data.size()<1){
            TextView textView = (TextView) View.inflate(context, R.layout.course_empty,null);
            container.addView(textView);
            return textView;
        } else {
            ListView listView = getDateListView(context,data.get(position));
            container.addView(listView);
            return listView;
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    public static CourseAdapter courseAdapter;

    private ListView getDateListView(Context context, List<Course> list) {

        courseAdapter=new CourseAdapter(context, list);
        ListView coursesList = new ListView(context);
      //  coursesList.setDivider(getResources().getDrawable(android.R.color.transparent));
        coursesList.setDividerHeight(0);
        coursesList.setAdapter(courseAdapter);

        return coursesList;
    }
}
