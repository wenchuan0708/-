package com.yongjian.gdufszhushou.Model;

/**
 * Created by YONGJIAN on 2016/3/9 0009.
 */
public class Course {
    private String CourseName;
    private String Teacher;
    private String Classroom;
    private String Time;
    private String ContinuedWeek;
    private String Id;

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setContinuedWeek(String continuedWeek) {
        ContinuedWeek = continuedWeek;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setClassroom(String classroom) {
        Classroom = classroom;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getId() {
        return Id;
    }

    public String getContinuedWeek() {
        return ContinuedWeek;
    }

    public String getTime() {
        return Time;
    }

    public String getClassroom() {
        return Classroom;
    }

    public String getTeacher() {
        return Teacher;
    }

    public String getCourseName() {
        return CourseName;
    }
}
