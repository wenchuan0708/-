package com.yongjian.gdufszhushou.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yongjian.gdufszhushou.Adapter.NoticeAdapter;
import com.yongjian.gdufszhushou.ItemDivider;
import com.yongjian.gdufszhushou.Model.Notice;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HttpUtil;

import java.util.ArrayList;

/**
 * Created by YONGJIAN on 2016/4/27 0027.
 */
public class NoticeFragment extends Fragment {


    public static RecyclerView recyclerView;
    public static RecyclerView.LayoutManager layoutManager = null;
    public static NoticeAdapter noticeAdapter= null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_title_list,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.noticerecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        noticeAdapter = new NoticeAdapter(new ArrayList<Notice>(HttpUtil.noticedatamap.values()),getActivity());
        recyclerView.setAdapter(noticeAdapter);
        recyclerView.addItemDecoration(new ItemDivider());
        return view;
    }
}
