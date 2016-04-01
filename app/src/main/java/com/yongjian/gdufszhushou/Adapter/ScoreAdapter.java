package com.yongjian.gdufszhushou.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yongjian.gdufszhushou.Model.Score;
import com.yongjian.gdufszhushou.R;


import org.jsoup.select.Evaluator;

import java.util.ArrayList;

/**
 * Created by YONGJIAN on 2016/3/16 0016.
 */
public class ScoreAdapter extends BaseAdapter {

    private ArrayList<Score> scoreData;
    private Context context;


    public ScoreAdapter(Context context, ArrayList<Score> scoreData) {
        this.context=context;
        this.scoreData=scoreData;
    }

    @Override
    public int getCount() {
        return scoreData.size();
    }

    @Override
    public Object getItem(int position) {
        return scoreData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.score_item_layout,null);
            holder.score = (TextView) convertView.findViewById(R.id.score);
            holder.score_credit =(TextView) convertView.findViewById(R.id.score_credit);
            holder.score_name =(TextView) convertView.findViewById(R.id.score_name);
            holder.score_point = (TextView)convertView.findViewById(R.id.score_point);
            holder.score_type =(TextView) convertView.findViewById(R.id.score_type);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        Score score = scoreData.get(position);
        if (score!=null) {
            int circleshade = 0;
            int ascore = Integer.parseInt(score.getScore());
            if (ascore >=90){
                circleshade = R.drawable.circle_amber;
            }else if (ascore>=80&&ascore<90){
                circleshade =R.drawable.circle_blue;
            }else if (ascore>=70&&ascore<80){
                circleshade = R.drawable.circle_brown;
            }else if (ascore>=60&&ascore<70){
                circleshade=R.drawable.circle_green;
            }else{
                circleshade = R.drawable.circle_deep_orange;
            }
            holder.score.setText(score.getScore());
            holder.score.setBackgroundResource(circleshade);
            holder.score_point.setText(score.getScorePoint());
            holder.score_type.setText(score.getType());
            holder.score_credit.setText(score.getCredit());
            holder.score_name.setText(score.getScoreName());
        }
        return convertView;
    }
    private  static class ViewHolder{
        TextView score;
        TextView score_type;
        TextView score_name;
        TextView score_credit;
        TextView score_point;
    }
}
