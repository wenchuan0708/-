package com.yongjian.gdufszhushou.Model;

/**
 * Created by YONGJIAN on 2016/4/21 0021.
 */
public class News {
    private String title;
    private String content;
    private String path;
    public static String NEWS_INDEX = "http://news.gdufs.edu.cn/Category_24/Index.aspx";
    public static String INDEX = "http://news.gdufs.edu.cn";

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
}
