package com.yongjian.gdufszhushou.Util;

import android.util.Log;

import com.yongjian.gdufszhushou.CallBack;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YONGJIAN on 2016/3/15 0015.
 */
public class HttpUtil {
    public static   String cookie = "";
    public static String userName = "";
    public static String password = "";


    public static void login(final CallBack callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getLoginCookie(callback);
            }
        }).start();
    }
    public static void getLoginCookie(final CallBack callback){
        final  String url = "http://jxgl.gdufs.edu.cn/jsxsd/xk/LoginToXkLdap";
        org.jsoup.Connection conn = Jsoup.connect(url);

        conn.data(getData());
        setHeader(conn);


        try {
            conn.post();
            Connection.Response rs = conn.execute();
         //   Log.d("tag",cookie);
            // / Log.d("tag",rs.url().toString());
            if (rs.url().toString().equals("http://jxgl.gdufs.edu.cn/jsxsd/framework/xsMain.jsp") ){
               cookie = rs.cookies().toString();
                callback.onStart();
            }else{
                callback.onFinsh(null);
            }

        }catch (Exception e){
            callback.onFinsh(null);
            e.printStackTrace();
        }

    }

    private static Map<String, String> getData() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("USERNAME", userName);
        data.put("PASSWORD", password);
        return data;
    }

    private static void setHeader(Connection conn) {
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Host","jxgl.gdufs.edu.cn")
                .header("Connection", "keep-alive")
                .header("Referer", "http://jxgl.gdufs.edu.cn/jsxsd/")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
    }
}

