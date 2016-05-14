package com.yongjian.gdufszhushou.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

/**
 * Created by YONGJIAN on 2016/5/14 0014.
 */
public class ReporterFragment extends Fragment {

    private TextView temp1 ;
    private TextView temp2 ;
    private TextView temp3 ;
    private TextView temp4 ;
    private TextView qi1 ;
    private TextView qi2 ;
    private TextView qi3 ;
    private TextView sugg ;
    private Button importn;
    private TextView suggestion;
    private LinearLayout layou1;
    private LinearLayout empty;
    String url  = "http://www.weather.com.cn/weather/101280101.shtml";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reporter_fragment,null);
        temp1 = (TextView) view.findViewById(R.id.temp1);
        temp2 = (TextView) view.findViewById(R.id.temp2);
        temp3 = (TextView) view.findViewById(R.id.temp3);
        temp4 = (TextView) view.findViewById(R.id.temp4);

        qi1 = (TextView) view.findViewById(R.id.qixiang1);
        qi2 = (TextView) view.findViewById(R.id.qixiang2);
        qi3 = (TextView) view.findViewById(R.id.qixiang3);
        importn = (Button) view.findViewById(R.id.impotbtn);
        layou1 = (LinearLayout) view.findViewById(R.id.layout1);
        empty  = (LinearLayout) view.findViewById(R.id.empty_layout);
        suggestion = (TextView) view.findViewById(R.id.suggestion);

        if (HandleResponseUtil.report.size()!=0)
            init();

        importn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.getHtmlUtil(getActivity(), url, new CallBack() {
                    @Override
                    public void onStart() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ProgressDialogHelper.showProgressDialog(getActivity(),
                                        "正在更新，这可能需要一点时间...");
                            }
                        });
                    }

                    @Override
                    public void onFinsh(String response) {

                        HandleResponseUtil.handleReporter(response, new CallBack() {
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onFinsh(String response) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        init();
                                        ProgressDialogHelper.closeProgressDialog();
                                    }
                                });
                            }
                        });

                    }
                }, Request.Method.GET,null,null);
            }
        });


        return view;
    }
    public void init(){
        temp1.setText(HandleResponseUtil.report.get("temp1"));
        temp2.setText(HandleResponseUtil.report.get("temp2"));
        temp3.setText(HandleResponseUtil.report.get("temp3"));
        temp4.setText(HandleResponseUtil.report.get("temp1"));
        qi1.setText(HandleResponseUtil.report.get("qi1"));
        qi2.setText(HandleResponseUtil.report.get("qi2"));
        qi3.setText(HandleResponseUtil.report.get("qi3"));
        suggestion.setText(HandleResponseUtil.sb.toString());
        layou1.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
    }
}
