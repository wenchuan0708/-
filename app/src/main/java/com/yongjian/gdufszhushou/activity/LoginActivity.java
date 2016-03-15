package com.yongjian.gdufszhushou.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HttpUtil;

/**
 * Created by YONGJIAN on 2016/3/15 0015.
 */
public class LoginActivity  extends Activity {

    private EditText userId;
    private EditText pwd;
    private Button loginbtn;
    private ProgressDialog  progressDialog;

    private String user="";
    private String pass="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        isGetCookie();

        loginbtn=(Button)findViewById(R.id.sign_in);
        userId =(EditText) findViewById(R.id.login_id);
        pwd = (EditText) findViewById(R.id.login_pwd);

        loginbtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user = userId.getText().toString();
                pass = pwd.getText().toString();
                saveUserandPass();
            }
        });
    }
    public void saveUserandPass(){
            if (TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)){
                new AlertDialog.Builder(this)
                               .setTitle("善意的提醒")
                               .setPositiveButton("确定",null)
                               .setMessage("请填写完整的学号和密码")
                               .show();
            }else
            {
                HttpUtil.userName = user;
                HttpUtil.password = pass;
                HttpUtil.login(new CallBack() {
                    @Override
                    public void onStart() {
                        saveCookie();
                        Log.d("TAG",HttpUtil.cookie);
                        Intent intent = new Intent(LoginActivity.this,DrawerLayoutActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFinsh(String response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(LoginActivity.this)
                                        .setTitle("善意的提醒")
                                        .setPositiveButton("确定",null)
                                        .setMessage("登陆失败，请确保学号和密码输入正确，并尝试再次登陆")
                                        .show();
                            }
                        });
                    }
                });
            }
    }

    private void isGetCookie() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getBoolean("cookie_Ok",false)){
            HttpUtil.cookie = prefs.getString("cookie",null);
            Intent intent = new Intent(LoginActivity.this,DrawerLayoutActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void saveCookie(){
        SharedPreferences.Editor edit =  PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putBoolean("cookie_Ok",true);
        edit.putString("cookie",HttpUtil.cookie);
        edit.commit();
    }
}
