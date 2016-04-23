package com.yongjian.gdufszhushou.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yongjian.gdufszhushou.Model.Course;
import com.yongjian.gdufszhushou.R;

import org.jsoup.select.Evaluator;

import java.util.List;

/**
 * Created by YONGJIAN on 2016/3/19 0019.
 */
public class CourseAdapter extends BaseAdapter {

    private Context context;
    private List<Course> data;

    public CourseAdapter( Context context,List<Course> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.course_item, null);
            holder.courseName = (TextView) convertView.findViewById(R.id.course_name);
            holder.courseRoom = (TextView) convertView.findViewById(R.id.course_room);
            holder.courseTeacher = (TextView) convertView.findViewById(R.id.teacher);
            holder.courseTime = (TextView) convertView.findViewById(R.id.time);
            holder.courseWeek = (TextView) convertView.findViewById(R.id.weekTime);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Course course = data.get(position);
        if(course !=null) {
            int circleShape = 0;
            String courseTime = course.getTime();
            if (courseTime.equals("08:30-09:50")) {
                circleShape =R.drawable.circle_amber;
            } else if (courseTime.equals("10:00-11:30")) {
                circleShape=R.drawable.circle_blue;
            } else if (courseTime.equals("11:30-12:15")) {
                circleShape=R.drawable.circle_brown;
            } else if (courseTime.equals("14:00-15:20")) {
                circleShape=R.drawable.circle_cyan;
            } else if (courseTime.equals("15:30-16:50")) {
                circleShape=R.drawable.circle_green;
            } else if (courseTime.equals("18:30-20:30")) {
                circleShape=R.drawable.circle_deep_orange;
            } else {
                circleShape =R.drawable.circle_orange;
            }

            holder.courseName.setText(course.getCourseName());
            holder.courseRoom.setText(course.getClassroom());
            holder.courseTeacher.setText(course.getTeacher());
            holder.courseTime.setText(course.getTime());
            holder.courseTime.setBackgroundResource(circleShape);
            holder.courseWeek.setText(course.getContinuedWeek());
        }
        return convertView;

    }


    private static  class ViewHolder{
        TextView courseName;
        TextView courseRoom;
        TextView courseTeacher;
        TextView courseTime;
        TextView courseWeek;
    }
}
