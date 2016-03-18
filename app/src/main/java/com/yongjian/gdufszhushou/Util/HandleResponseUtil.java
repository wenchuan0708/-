package com.yongjian.gdufszhushou.Util;

import android.os.AsyncTask;
import android.util.Log;

import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Db.Db;
import com.yongjian.gdufszhushou.Model.Score;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by YONGJIAN on 2016/3/15 0015.
 */
public class HandleResponseUtil {

    public static ArrayList<Score> scores=new ArrayList<Score>();
    public static Db db=null;
    public static String aveScore;

    public static void handleScoreHtmlStr(String htmlStr, final CallBack callBack){
        new AsyncTask<String,Integer,Boolean>(){

            @Override
            protected Boolean doInBackground(String... params) {
                scores.clear();
                int sum = 0;
                boolean result =false;
                try{
                    Document document = Jsoup.parse(params[0]);
                    //Elements ele = document.select("table").select("tr")
                    Log.d("AAA","CC");
                    Log.d("AAA","CCCCC");
                    Elements span = document.select("span");
                    aveScore = span.get(7).text();
                    //Log.d("AAA",span.get(7).toString());


                    Elements ele = document.getElementsByTag("table");
              //      Log.d("AAA",ele.toString());
                   Log.d("AAA","222");
                    Elements trs = ele.select("tr");
                    for (int i= 2;i<trs.size();i++){
                        Element tds = trs.get(i);
                        Score score = new Score();
                        score.setScoreName(tds.select("td").get(3).text());
                       // Log.d("AAA",tds.select("td").get(3).text());
                        score.setScore(tds.select("td").get(4).text());
                       // Log.d("AAA",tds.select("td").get(4).text());
                        score.setCredit(tds.select("td").get(5).text()+"学分");
                       // Log.d("AAA",tds.select("td").get(5).text());
                        score.setType(tds.select("td").get(8).text());
                       // Log.d("AAA",tds.select("td").get(8).text());
                        score.setScorePoint(handleScoretoPoint(tds.select("td").get(4).text()));
                        db.saveScore(score);
                        scores.add(score);
                    }


                    result= true;

                }catch (Exception e){
                    result =false;
                    Log.d("winson", "解析错误： " + e);
                }
                return result;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                    callBack.onFinsh("");
                else
                    Log.d("TAG","handle Score failed");
            }
        }.execute(htmlStr);
    }
    public static String handleScoretoPoint(String s ) {
        int number = Integer.parseInt(s);
        String point = null;
        if (number < 60) {
            point = "0.0";
        } else if (number < 64 && number >= 60) {
            point = "1.0";
        } else if (number < 68 && number > 63) {
            point = "1.7";
        } else if (number < 72 && number > 67) {
            point = "2.0";
        } else if (number < 75 && number > 71) {
            point = "2.3";
        } else if (number < 78 && number > 74) {
            point = "2.7";
        } else if (number < 82 && number > 77) {
            point = "3.0";
        } else if (number < 85 && number > 81) {
            point = "3.3";
        } else if (number < 90 && number > 84) {
            point = "3.7";
        } else if (number > 89) {
            point = "4.0";
        }
        return point;
    }
}
