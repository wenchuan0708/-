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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.R;
import com.yongjian.gdufszhushou.Util.HttpUtil;
import com.yongjian.gdufszhushou.Widge.AlertDialogHelper;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;

/**
 * Created by YONGJIAN on 2016/3/15 0015.
 */
public class LoginActivity  extends Activity {

    private EditText userId;
    private EditText pwd;
    private Button loginbtn;
    private String user="";
    private String pass="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        loginbtn=(Button)findViewById(R.id.sign_in);
        userId =(EditText) findViewById(R.id.login_id);
        pwd = (EditText) findViewById(R.id.login_pwd);
        if(!(getIntent().getBooleanExtra("return",false)))
        {
            isSetUser();
            if (!(TextUtils.isEmpty(userId.getText().toString())))
            {
                ProgressDialogHelper.showProgressDialog(LoginActivity.this,"正在登录...请稍候");
                user = userId.getText().toString();
                pass = pwd.getText().toString();
                saveUserAndPass();
            }
        }

        loginbtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ProgressDialogHelper.showProgressDialog(LoginActivity.this,"正在登录...请稍候");
                user = userId.getText().toString();
                pass = pwd.getText().toString();
                saveUserAndPass();
            }
        });
    }
    public void saveUserAndPass(){
            if (TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)){
                ProgressDialogHelper.closeProgressDialog();
                AlertDialogHelper.showAlertDialog(LoginActivity.this,"善意的提醒","请填写完整的学号和密码");
            }else
            {
                HttpUtil.userName = user;
                HttpUtil.password = pass;
                HttpUtil.login(new CallBack() {
                    @Override
                    public void onStart() {
                        ProgressDialogHelper.closeProgressDialog();
                        saveUser();
                        Intent intent = new Intent(LoginActivity.this,DrawerLayoutActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFinsh(String response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ProgressDialogHelper.closeProgressDialog();
                                AlertDialogHelper.showAlertDialog(LoginActivity.this,"善意的提醒","登陆失败，请确保学号和密码输入正确，并尝试再次登陆");
                            }
                        });
                    }
                });
            }
    }

    private void isSetUser() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getBoolean("store_OK",false)){
            HttpUtil.userName = prefs.getString("username",null);
            HttpUtil.password = prefs.getString("password",null);
            userId.setText(HttpUtil.userName);
            pwd.setText(HttpUtil.password);
        }
    }
    private void saveUser() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putBoolean("store_OK", true);
        edit.putString("username", HttpUtil.userName);
        edit.putString("password",HttpUtil.password);
        edit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
