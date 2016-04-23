package com.yongjian.gdufszhushou.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yongjian.gdufszhushou.Adapter.NewsAdapter;
import com.yongjian.gdufszhushou.ItemDivider;
import com.yongjian.gdufszhushou.Model.News;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HttpUtil;

import java.util.ArrayList;

/**
 * Created by YONGJIAN on 2016/4/21 0021.
 */
public class NewsFramgment extends Fragment {

    public static RecyclerView recyclerView;
    public static RecyclerView.LayoutManager layoutManager = null;
    public static NewsAdapter newsAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_list,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(new ArrayList<News>(HttpUtil.datamap.values()),getActivity());
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addItemDecoration(new ItemDivider());
        return view;
    }
}
