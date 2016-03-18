package com.yongjian.gdufszhushou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yongjian.gdufszhushou.Fragment.ScoreFragment;
import com.yongjian.gdufszhushou.R;

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


        drawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);

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
                        }
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        return false;
                    }
                }
        );
    }
    private void switchToCourse(){

    }
    private void switchToNews(){

    }
    private void switchToScore(){
        getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,new ScoreFragment()).commit();
        toolbar.setTitle("绩点查询");
    }
    private void switchToAbout(){

    }
}
