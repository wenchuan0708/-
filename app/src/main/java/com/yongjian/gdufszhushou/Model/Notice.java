package com.yongjian.gdufszhushou.Model;

/**
 * Created by YONGJIAN on 2016/4/27 0027.
 */
public class Notice {

    private String title;
    private String content;
    private String path;
    private String date;


    public static String NOTICE_INDEX = "http://www.gdufs.edu.cn/tzgg/37.htm";
    public static String INDEX = "http://www.gdufs.edu.cn";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
