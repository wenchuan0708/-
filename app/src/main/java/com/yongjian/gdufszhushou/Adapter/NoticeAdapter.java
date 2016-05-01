package com.yongjian.gdufszhushou.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yongjian.gdufszhushou.Model.News;
import com.yongjian.gdufszhushou.Model.Notice;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.activity.NewsActivity;
import com.yongjian.gdufszhushou.activity.NoticeActivity;

import java.util.ArrayList;

/**
 * Created by YONGJIAN on 2016/4/27 0027.
 */
public class NoticeAdapter extends RecyclerView.Adapter {
    private ArrayList<Notice> dataset;
    public static Context context;

    public NoticeAdapter(ArrayList<Notice> dataset,Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item_layout,parent,false);
        ViewHolder mvh = new ViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mvh = (ViewHolder) holder;
        mvh.textView.setText(dataset.get(position).getTitle());
        mvh.DateView.setText(dataset.get(position).getDate());
        holder.itemView.setTag(dataset.get(position));
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textView = null;
        private TextView DateView = null;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.noticetitle);
            DateView = (TextView) itemView.findViewById(R.id.noticedate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            NoticeActivity.startNoticeActivity(context, (Notice) v.getTag());
        }
    }
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
