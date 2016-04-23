package com.yongjian.gdufszhushou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Model.News;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

/**
 * Created by YONGJIAN on 2016/4/23 0023.
 */
public class NewsActivity extends AppCompatActivity {

    private TextView news_title;
    private TextView news_content;
    private TextView news_date;
    private ScrollView scrollView;
    private static News mNews;
    public static void startNewsActivity(Context context, News news) {
        Intent intent = new Intent(context, NewsActivity.class);
        mNews=news;
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_layout);
        news_title = (TextView) findViewById(R.id.news_title);
        news_content = (TextView) findViewById(R.id.news_content);
        news_date = (TextView) findViewById(R.id.news_date);
        scrollView = (ScrollView) findViewById(R.id.news_scrollView);
        ProgressDialogHelper.showProgressDialog(this,"正在加载，请稍后");
        HttpUtil.getHtmlUtil(this, mNews.getPath(), new CallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh(String response) {
                HandleResponseUtil.parseContentData(response);
                ProgressDialogHelper.closeProgressDialog();
                news_title.setText(mNews.getTitle());
                news_content.setText(mNews.getContent());
                news_date.setText(mNews.getDate());

            }
        }, Request.Method.GET,null,null);
    }
}
