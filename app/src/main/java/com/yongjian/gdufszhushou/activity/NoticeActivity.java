package com.yongjian.gdufszhushou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Model.News;
import com.yongjian.gdufszhushou.Model.Notice;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

/**
 * Created by YONGJIAN on 2016/4/27 0027.
 */
public class NoticeActivity extends AppCompatActivity {

    private TextView notice_title;
    private TextView notice_date;
    private TextView notice_content;
    private static Notice mNotice;

    public static void startNoticeActivity(Context context, Notice notice) {
        Intent intent = new Intent(context, NoticeActivity.class);
        mNotice=notice;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_activity_layout);
        notice_title = (TextView) findViewById(R.id.notice_title);
        notice_date= (TextView) findViewById(R.id.notice_date);
        notice_content = (TextView) findViewById(R.id.notice_content);
        ProgressDialogHelper.showProgressDialog(this,"正在加载，请稍后");
        HttpUtil.getHtmlUtil(this, mNotice.getPath(), new CallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh(String response) {
                HandleResponseUtil.parseNoticeContent(response);
                ProgressDialogHelper.closeProgressDialog();
                notice_title.setText(mNotice.getTitle());
                notice_content.setText(mNotice.getContent());
                notice_date.setText(mNotice.getDate());

            }
        }, Request.Method.GET,null,null);
    }
    }

