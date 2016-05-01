package com.yongjian.gdufszhushou.Fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yongjian.gdufszhushou.Adapter.ScoreAdapter;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Db.Db;
import com.yongjian.gdufszhushou.Model.Score;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

import java.util.ArrayList;

/**
 * Created by YONGJIAN on 2016/3/16 0016.
 */
public class ScoreFragment extends Fragment {

    private LinearLayout emptyLinearLayout;
    private LinearLayout scoreLinearLayout;
    private TextView aveTextView;
    private ListView listView;
    private Button importbtn;
    private ArrayList<Score> scoreData = HandleResponseUtil.scores;
    private SwipeRefreshLayout swipeRefreshLayout;
    int flag = 0;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.score_fragment,null);
        emptyLinearLayout = (LinearLayout) view.findViewById(R.id.empty_layout);
        scoreLinearLayout = (LinearLayout) view.findViewById(R.id.score_layout);
        aveTextView = (TextView) view.findViewById(R.id.ave_score);
        listView = (ListView) view.findViewById(R.id.score_lv);
        importbtn = (Button) view.findViewById(R.id.impotbtn);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_bright);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpUtil.getScoreHtml(getActivity(), new CallBack() {
                    @Override
                    public void onStart() {
                        swipeRefreshLayout.setRefreshing(true);

                    }

                    @Override
                    public void onFinsh(String response) {
                        saveAvecore();
                        initView();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });

      // findFromDb();
        if (scoreData.size()!=0){
            initView();
        }
        importbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                HttpUtil.getScoreHtml(getActivity(), new CallBack() {
                    @Override
                    public void onStart() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ProgressDialogHelper.showProgressDialog(getActivity(),
                                        "正在导入，这可能需要一点时间...");
                            }
                        });
                    }

                    @Override
                    public void onFinsh(String response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                saveAvecore();
                                initView();
                                flag =1;
                                ProgressDialogHelper.closeProgressDialog();
                            }
                        });
                        Log.d("TAG", "handle score OK");
                    }
                });
            }
        });


        return view;
    }
//    public void findFromDb(){
//        if (scoreData.size() ==0){
//            if (HandleResponseUtil.db ==null){
//                HandleResponseUtil.db = Db.getInstance(getActivity());
//            }
//            if (HandleResponseUtil.db != null&&flag ==1){
//                if (HandleResponseUtil.db.loadScore()){
//                    initView();
//                }
//            }
//        }else{
//            initView();
//        }
//    }
    public void initView(){
        setAveScore();
        listView.clearFocus();
        listView.setAdapter(new ScoreAdapter(getActivity(),scoreData));
        listView.setVisibility(View.VISIBLE);
        emptyLinearLayout.setVisibility(View.GONE);
        scoreLinearLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    public void setAveScore(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        aveTextView.setText(prefs.getString("aveScore",null));
    }
    public void saveAvecore(){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
        editor.putString("aveScore",HandleResponseUtil.aveScore);
        editor.commit();
    }


}
