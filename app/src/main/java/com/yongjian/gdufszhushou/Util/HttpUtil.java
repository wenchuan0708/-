package com.yongjian.gdufszhushou.Util;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Model.News;
import com.yongjian.gdufszhushou.Widge.AlertDialogHelper;
import com.yongjian.gdufszhushou.Widge.ProgressDialogHelper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by YONGJIAN on 2016/3/15 0015.
 */
public class HttpUtil {
    public static LinkedHashMap<String, News> datamap = new LinkedHashMap<String, News>();
    public static String cookie = "";
    public static String userName = "";
    public static String password = "";
    public static Context mcontext;
    public static String path;
    private static boolean isGBK = false;

    public static void login(final CallBack callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getLoginCookie(callback);
            }
        }).start();
    }

    public static void getLoginCookie(final CallBack callback) {
        final String url = "http://jxgl.gdufs.edu.cn/jsxsd/xk/LoginToXkLdap";
        Connection conn = Jsoup.connect(url);

        conn.data(getData());
        setHeader(conn);
        //conn.request().followRedirects(false);

        try {
            conn.post();
            org.jsoup.Connection.Response rs = conn.response();
            cookie = rs.cookies().toString();
            cookie = cookie.substring(1, cookie.length() - 1);
//            Log.d("AAA",cookie);
//            Log.d("AAA",rs.url().toString());
////            Log.d("CCC",rs.body());
////            Log.d("CCC","1111");
//            Log.d("AAA",rs.statusMessage());
//            Log.d("AAA","1111");
            if (rs.url().toString().equals("http://jxgl.gdufs.edu.cn/jsxsd/framework/xsMain.jsp")) {
                cookie = rs.cookies().toString();
                cookie = cookie.substring(1, cookie.length() - 1);
                Log.d("CCC", cookie);
                callback.onStart();
            } else {
                callback.onFinsh(null);
            }

        } catch (Exception e) {
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
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8")
                .header("Cache-Control", "max-age=0")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Host", "jxgl.gdufs.edu.cn")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                .header("Referer", "http://jxgl.gdufs.edu.cn/jsxsd/");
    }

    public static void getHtmlUtil(Context context, String url, final CallBack callBack, final int Method, final Map<String, String> headers, final Map<String, String> map) {
        callBack.onStart();
        mcontext = context;
        StringRequest stringRequest = null;
        RequestQueue mQueue = Volley.newRequestQueue(context);

        if (Method == Request.Method.POST) {
            stringRequest = new StringRequest(Method, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String s) {
                    String a = s.substring(s.length() - 100, s.length() - 1);
                    Log.d("CCC", a);
                    Log.d("CCC", s);

                    callBack.onFinsh(s);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    ProgressDialogHelper.closeProgressDialog();
                    AlertDialogHelper.showAlertDialog(mcontext, "出问题了", "导入失败，请检查你的网络,再次尝试");
                    Log.e("TAG", volleyError.getMessage(), volleyError);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() {
                    return map;
                }
//
            };
        } else {
            if(url.substring(0,1).equals("/")==true){
                path=url;
                url=News.INDEX+path;
            }
            stringRequest = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Log.d("CCC", "这是正确1");
                            Log.d("CCC", s);
                            Log.d("CCC", "这是正确2");
                            callBack.onFinsh(s);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.d("CCC", "这是错误的");
                    ProgressDialogHelper.closeProgressDialog();
                    AlertDialogHelper.showAlertDialog(mcontext, "出问题了", "导入失败，请检查你的网络,再次尝试");
                    Log.e("TAG", volleyError.getMessage(), volleyError);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    if (headers != null) {
                        return headers;
                    } else {
                        return super.getHeaders();
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {

                    String result = "";
                    try {
                        if(isGBK){
                            result = new String(response.data, "GB2312");
                            isGBK=false;
                        }
                        else
                            result = new String(response.data, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
                }
            };


        }
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(12000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(stringRequest);
        mQueue.start();

    }

    public static void getScoreHtml(final Context context, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callBack.onStart();
                String url = "http://jxgl.gdufs.edu.cn/jsxsd/kscj/cjcx_list";
                Map<String, String> headers = new HashMap<String, String>();

                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Encoding", "gzip, deflate");
                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                headers.put("Cache-Control", "max-age=0");
                headers.put("Connection", "keep-alive");
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Cookie", cookie);
                headers.put("Host", "jxgl.gdufs.edu.cn");
                headers.put("Origin", "http://jxgl.gdufs.edu.cn");
                headers.put("Referer", "http://jxgl.gdufs.edu.cn/jsxsd/kscj/cjcx_query");
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
                //   Log.d("AAA",cookie);
                isGBK = false;
                Map<String, String> map = new HashMap<String, String>();
                map.put("kksj", " ");
                map.put("kcxz", " ");
                map.put("kcmc", " ");
                map.put("xsfs", "all");


                getHtmlUtil(context, url, new CallBack() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinsh(String response) {
                        HandleResponseUtil.handleScoreHtmlStr(response, callBack);
                    }
                }, Request.Method.POST, headers, map);

            }
        }).start();
    }

    public static void getCorseHtml(final Context context, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callBack.onStart();
                String url = "http://jxgl.gdufs.edu.cn/jsxsd/xskb/xskb_list.do?Ves632DSdyV=NEW_XSD_PYGL";
                Map<String, String> headers = new HashMap<String, String>();

                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Encoding", "gzip, deflate, sdch");
                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
                headers.put("Connection", "keep-alive");
                headers.put("Cookie", cookie);
                headers.put("Host", "jxgl.gdufs.edu.cn");
                headers.put("Referer", "http://jxgl.gdufs.edu.cn/jsxsd/framework/main.jsp");
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");

                getHtmlUtil(context, url, new CallBack() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinsh(String response) {
                        HandleResponseUtil.handleCourseHtmlStr(response, callBack);
                    }
                }, Request.Method.GET, headers, null);
            }
        }).start();
    }


}

