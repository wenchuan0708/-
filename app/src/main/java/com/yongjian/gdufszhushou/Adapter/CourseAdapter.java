package com.yongjian.gdufszhushou.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yongjian.gdufszhushou.Model.Course;
import com.yongjian.gdufszhushou.R;

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
        holder.courseName.setText(course.getCourseName());
        holder.courseRoom.setText(course.getClassroom());
        holder.courseTeacher.setText(course.getTeacher());
        holder.courseTime.setText(course.getTime());
        holder.courseWeek.setText(course.getContinuedWeek());

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
