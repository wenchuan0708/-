package com.yongjian.gdufszhushou.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yongjian.gdufszhushou.Model.Course;
import com.yongjian.gdufszhushou.Model.PlanCourse;
import com.yongjian.gdufszhushou.R;

import java.util.List;

/**
 * Created by YONGJIAN on 2016/4/30 0030.
 */
public class PlanCourseAdapter extends BaseAdapter {

    private Context context;
    private List<PlanCourse> data;

    public PlanCourseAdapter(Context context,List<PlanCourse> data) {
        this.context = context;
        this.data = data;
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
        ViewHolder viewHolder = null;
        if (convertView ==null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.plan_course_item,null);
            viewHolder.cName = (TextView) convertView.findViewById(R.id.cname);
            viewHolder.cTime = (TextView) convertView.findViewById(R.id.ctime);
            viewHolder.cCredit = (TextView) convertView.findViewById(R.id.ccredit);
            viewHolder.cType = (TextView) convertView.findViewById(R.id.ctype);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlanCourse planCourse = data.get(position);


        viewHolder.cCredit.setText("学分"+planCourse.getcCredit());
        viewHolder.cType.setText(planCourse.getcType());
        viewHolder.cTime.setText(planCourse.getcTime());
        viewHolder.cName.setText(planCourse.getcName());


        return convertView;
    }
    private static class ViewHolder{
        private TextView cName;
        private TextView cType;
        private TextView cTime;
        private TextView cCredit;
    }
}
