package com.yongjian.gdufszhushou.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.yongjian.gdufszhushou.Adapter.ViewPagerAdapter;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Db.Db;
import com.yongjian.gdufszhushou.Model.Course;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

import java.util.Calendar;
import java.util.List;

/**
 * Created by YONGJIAN on 2016/3/18 0018.
 */
public class CourseFragment extends Fragment {
    private Button importbtn;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPagerAdapter vpAdapter;
    private ViewPager viewPager;
    private LinearLayout emptyLayout;
    private List<List<Course>> data = HandleResponseUtil.courseData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_fragment,null);
        importbtn = (Button) view.findViewById(R.id.impotbtn);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tab_indicator);
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        emptyLayout = (LinearLayout) view.findViewById(R.id.empty_layout);

        if (HandleResponseUtil.db == null)
            HandleResponseUtil.db = Db.getInstance(getActivity());

        importbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.getCorseHtml(getActivity(), new CallBack() {
                    @Override
                    public void onStart() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ProgressDialogHelper.showProgressDialog(getActivity(),"正在导入，这可能需要一点时间...");
                            }
                        });

                    }
                    @Override
                    public void onFinsh(String response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initViewPage();
                                ProgressDialogHelper.closeProgressDialog();
                            }
                        });
                    }
                });
            }
        });
        return view;
    }
    public void initViewPage(){
        vpAdapter = new ViewPagerAdapter(getActivity(),data);
        viewPager.setAdapter(vpAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        emptyLayout.setVisibility(View.GONE);
        whatDayIs();
    }
    private void whatDayIs() {
        Calendar calendar = Calendar.getInstance();
        int date = 0;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                date = 0;
                break;
            case Calendar.TUESDAY:
                date = 1;
                break;
            case Calendar.WEDNESDAY:
                date = 2;
                break;
            case Calendar.THURSDAY:
                date = 3;
                break;
            case Calendar.FRIDAY:
                date = 4;
                break;
            case Calendar.SATURDAY:
                date = 5;
                break;
            case Calendar.SUNDAY:
                date = 6;
                break;
        }
        viewPager.setCurrentItem(date);
    }
}
