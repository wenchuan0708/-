package com.yongjian.gdufszhushou.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yongjian.gdufszhushou.Adapter.PlanCourseAdapter;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

import java.util.zip.Inflater;

/**
 * Created by YONGJIAN on 2016/4/30 0030.
 */
public class PlanCourseFragment extends Fragment {


    private Button button;
    private LinearLayout linearLayout;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.plancourse_fragment,null);
        button = (Button) view.findViewById(R.id.impotbtn);
        linearLayout = (LinearLayout) view.findViewById(R.id.empty_layout);
        listView = (ListView) view.findViewById(R.id.plan_list);
        if (!(HandleResponseUtil.planCoursesList.size()==0)){
            initView();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.getPlanHtml(getActivity(), new CallBack() {
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
                                initView();
                                ProgressDialogHelper.closeProgressDialog();
                            }
                        });
                    }
                });
            }
        });
        return view;
    }

    private void initView(){
        listView.clearFocus();
        listView.setAdapter(new PlanCourseAdapter(getActivity(), HandleResponseUtil.planCoursesList));
        listView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }
}
