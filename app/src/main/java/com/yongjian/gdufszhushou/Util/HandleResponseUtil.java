package com.yongjian.gdufszhushou.Util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yongjian.gdufszhushou.Adapter.NewsAdapter;
import com.yongjian.gdufszhushou.CallBack;
import com.yongjian.gdufszhushou.Db.Db;
import com.yongjian.gdufszhushou.Fragment.NewsFramgment;
import com.yongjian.gdufszhushou.Model.Course;
import com.yongjian.gdufszhushou.Model.News;
import com.yongjian.gdufszhushou.Model.Score;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YONGJIAN on 2016/3/15 0015.
 */
public class HandleResponseUtil {

    public static ArrayList<Score> scores=new ArrayList<Score>();
    public static Db db=null;
    public static String aveScore;

    public static void parseTitleData(String response){
        if (response!=null &&!"".equals(response)){
            Log.d("CCC","response不为空");
            Document doc = Jsoup.parse(response);
            Log.d("CCC",doc.toString());
            Element element = doc.getElementsByAttributeValue("class","pageTPList").first();
            Log.d("CCC",element.toString());
            Elements elements = element.select("li").select("div[class=title]>a");

            Log.d("CCc","1111");
            Log.d("CCC",elements.toString());
            for (Element ele:elements){
                String href = ele.attr("href");
                Log.d("CCC",href);
                String title = ele.text();
                Log.d("CCC",title);
                News news = new News();
                news.setTitle(title);
                news.setPath(href);
                HttpUtil.datamap.put(news.getPath(),news);
            }
            NewsFramgment.newsAdapter=new NewsAdapter(new ArrayList<News>(HttpUtil.datamap.values()), NewsAdapter.context);
            NewsFramgment.recyclerView.setAdapter(NewsFramgment.newsAdapter);
            NewsFramgment.newsAdapter.notifyDataSetChanged();
        }else{
            Log.d("CCC","response为空");
        }
    }

    public static void parseContentData(String response){
        if (response!=null &&!"".equals(response)){
            Document document = Jsoup.parse(response);
            Log.d("CCC",document.toString());
            Elements pElements  = document.select("p");
            Log.d("CCC",pElements.toString());
            Log.d("CCC","111");
            StringBuilder sb = new StringBuilder();
            for (Element e : pElements) {
                String str = e.text();
                Log.d("CCC",str);
                Log.d("CCC","1111");
                sb.append(str + "\n");
            }
            HttpUtil.datamap.get(HttpUtil.path).setContent(sb.toString());
        }
    }

    public static void handleScoreHtmlStr(String htmlStr, final CallBack callBack){
        new AsyncTask<String,Integer,Boolean>(){

            @Override
            protected Boolean doInBackground(String... params) {
                scores.clear();
                boolean result =false;
                try{
                    Document document = Jsoup.parse(params[0]);
                    //Elements ele = document.select("table").select("tr")
//                    Log.d("AAA","CC");
//                    Log.d("AAA","CCCCC");
                    Elements span = document.select("span");
                    aveScore = span.get(7).text();
                    //Log.d("AAA",span.get(7).toString());


                    Elements ele = document.getElementsByTag("table");
              //      Log.d("AAA",ele.toString());
              //     Log.d("AAA","222");
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

    public static ArrayList<List<Course>> courseData=new ArrayList<List<Course>>();
    public static List<Course> list1 = new ArrayList<Course>();
    public static List<Course> list2 = new ArrayList<Course>();
    public static List<Course> list3 = new ArrayList<Course>();
    public static List<Course> list4 = new ArrayList<Course>();
    public static List<Course> list5 = new ArrayList<Course>();
    public static List<Course> list6 = new ArrayList<Course>();
    public static List<Course> list7 = new ArrayList<Course>();

    public static void handleCourseHtmlStr(String htmlStr,final  CallBack callBack){
        new AsyncTask<String, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                Boolean result=false;
                clear();
             try{
                 Document document = Jsoup.parse(params[0]);

                Elements elements = document.getElementsByTag("table");
                 //Log.d("CCC",elements.toString());
                Elements trs  =elements.select("tr");
                 Log.d("CCC",trs.toString());
                 Log.d("BBB",trs.get(2).select("td").text());
                 Log.d("CCC",trs.get(3).select("td").text());
                 Log.d("CCC",trs.get(8).select("td").get(0).text()) ;
                 Log.d("BBB",trs.get(7).select("td").get(1).text()) ;
                 Log.d("CCC","111111");
                for (int i= 0;i<=6;i++){
                    for (int j=2;j<=8;j++) {
                       if (trs.get(j).select("td").get(i).text().length()>15) {
                           String s = trs.get(j).select("td").get(i).text();
                           handleCourseSpilt(i,j,s);
                       }
                    }
                }
                 addToCourseData();
                 result=true;
             } catch (Exception e){
                 result=false;
                 Log.d("winson", "解析错误： " + e);
             }
                return  result;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result){
                    callBack.onFinsh("");
                }else
                    Log.d("TAG","handle course failed");
            }
        }.execute(htmlStr);
    }
    public static void handleCourseSpilt(int i,int j, String s){
        Course course = new Course();
        Log.d("CCCCCC",s);
       // s = s.replaceAll("\\(.*\\)","");
       // Log.d("CCCCC",s);
        String []result = s.split(" ");
        course.setCourseName(result[0]);
        Log.d("CCC",result[0]);
        course.setContinuedWeek(result[1]);
        Log.d("CCC",result[1]);
        course.setClassroom(result[2]);
        Log.d("CCC",result[2]);
        course.setTeacher(result[4]);
        Log.d("CCC",result[4]);
        course.setTime(switchTime(j));
        if (i == 0){
            course.setId("1");
            list1.add(course);
        }else if (i==1){
            course.setId("2");
            list2.add(course);
        }else if (i==2){
            course.setId("3");
            list3.add(course);
        }else if (i==3){
            course.setId("4");
            list4.add(course);
        }else if (i==4){
            course.setId("5");
            list5.add(course);
        }else if (i==5){
            course.setId("6");
            list6.add(course);
        }else {
            course.setId("7");
            list7.add(course);
        }
        db.saveCourse(course);
    }
    public static String switchTime(int j){
        int a = j-1;
        if (a== 1){
            return "08:30-09:50";
        }else if (a ==2) {
            return "10:00-11:30";
        }else if (a ==3) {
            return "11:30-12:15";
        }else if (a ==4) {
            return "14:00-15:20";
        }else if (a ==5) {
            return "15:30-16:50";
        }else if (a ==6) {
            return "18:30-20:30";
        }else
            return "20:30-21:20";
    }
    public static void addToCourseData() {
        if(courseData.size()==0){
            courseData.add(list1);
            courseData.add(list2);
            courseData.add(list3);
            courseData.add(list4);
            courseData.add(list5);
            courseData.add(list6);
            courseData.add(list7);
        }
    }
    public static void addToList(String id, Course course) {
        switch (id){
            case "1":
                list1.add(course);
                break;
            case "2":
                list2.add(course);
                break;
            case "3":
                list3.add(course);
                break;
            case "4":
                list4.add(course);
                break;
            case "5":
                list5.add(course);
                break;
            case "6":
                list6.add(course);
                break;
            case "7":
                list7.add(course);
                break;
        }
    }
    public static void clear(){
        courseData.clear();
        list1.clear();
        list2.clear();
        list3.clear();
        list3.clear();
        list5.clear();
        list6.clear();
        list7.clear();
    }
}
