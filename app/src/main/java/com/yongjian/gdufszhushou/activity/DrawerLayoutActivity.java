package com.yongjian.gdufszhushou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Fragment.AboutFragment;
import com.yongjian.gdufszhushou.Fragment.CalendarFragment;
import com.yongjian.gdufszhushou.Fragment.CourseFragment;
import com.yongjian.gdufszhushou.Fragment.NewsFramgment;
import com.yongjian.gdufszhushou.Fragment.NoticeFragment;
import com.yongjian.gdufszhushou.Fragment.PlanCourseFragment;
import com.yongjian.gdufszhushou.Fragment.ScoreFragment;
import com.yongjian.gdufszhushou.Model.News;
import com.yongjian.gdufszhushou.Model.Notice;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

/**
 * Created by YONGJIAN on 2016/3/15 0015.
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProgressDialog progressDialog;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(onMenuItemClick);


        drawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);

        switchToNews();
    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()){

                            case R.id.school_course:
                                switchToCourse();
                                break;
                            case R.id.school_news:
                                switchToNews();
                                break;
                            case R.id.school_score:
                                switchToScore();
                                break;
                            case R.id.about:
                                switchToAbout();
                                break;
                            case R.id.school_notice:
                               switchToNotice();
                                break;
                            case R.id.school_calendar:
                                switchToCalenter();
                                break;
                            case R.id.plan_course:
                                switchToPlanCourse ();
                        }
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        return false;
                    }
                }
        );
    }

    private void switchToNews(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new NewsFramgment()).commit();
       if(HttpUtil.datamap.values().size()==0){
           ProgressDialogHelper.showProgressDialog(this,"正在加载....请稍候");
            HttpUtil.getHtmlUtil(this, News.NEWS_INDEX, new CallBack() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinsh(String response) {
                    HandleResponseUtil.parseTitleData(response);
                    ProgressDialogHelper.closeProgressDialog();
                }
            }, Request.Method.GET, null,null);
        }
        toolbar.setTitle("学院要闻");
    }
    private void switchToNotice(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new NoticeFragment()).commit();
        if(HttpUtil.noticedatamap.values().size()==0){
            ProgressDialogHelper.showProgressDialog(this,"正在加载....请稍候");
            HttpUtil.getHtmlUtil(this, Notice.NOTICE_INDEX, new CallBack() {
                @Override
                public void onStart() {
                }
                @Override
                public void onFinsh(String response) {
                    HandleResponseUtil.parseNoticeTitle(response);
                    ProgressDialogHelper.closeProgressDialog();
                }
            }, Request.Method.GET, null,null);
        }
        toolbar.setTitle("通知公告");

    }
    private void switchToCourse(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new CourseFragment()).commit();
        toolbar.setTitle("课表查询");
    }
    private void switchToScore(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new ScoreFragment()).commit();
        toolbar.setTitle("绩点查询");
    }
    private void switchToPlanCourse(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new PlanCourseFragment()).commit();
        toolbar.setTitle("培养方案");
    }
    private void switchToCalenter(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new CalendarFragment()).commit();
        toolbar.setTitle("广外校历");
    }
    private void switchToAbout(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new AboutFragment()).commit();
        toolbar.setTitle("关于我");

    }
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_settings:
                    Intent i = new Intent(DrawerLayoutActivity.this,LoginActivity.class);
                    i.putExtra("return",true);
                    startActivity(i);
                    HandleResponseUtil.scores.clear();
                    HandleResponseUtil.courseData.clear();
                    HandleResponseUtil.planCoursesList.clear();
                    DrawerLayoutActivity.this.finish();
                    break;
            }
            return true;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.return_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
