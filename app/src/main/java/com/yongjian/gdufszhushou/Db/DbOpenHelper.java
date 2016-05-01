package com.yongjian.gdufszhushou.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YONGJIAN on 2016/3/16 0016.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private Context mcontext = null;

    private static final String CREATE_SCORE = "create table Score ("
            + "id integer primary key autoincrement,"
            + "ScoreName text,"
            + "ScorePoint text,"
            + "ScoreGrade text,"
            + "ScoreType text,"
            + "ScoreCredit text)";

    private static final String CREATE_COURSE = "create table Course("
            +"id integer primary key autoincrement"
            +"CourseName text"
            +"CourseTime text"
            +"CourseWeek text"
            +"CourseRoom text"
            +"CourseTeacher text)";

    private static final String CREATE_PLAN = "create table Plan("
            +"id integer primary key autoincrement"
            +"PlanName text"
            +"PlanType text"
            +"PlanCredit text"
            +"PlanTime text)";
    private static final String CREATE_NEWS = "create table News("
            +"id integer primary key autoincrement"
            +"NewsTitle text"
            +"NewsContent text"
            +"NewsDate"
            +"NewsPath text)";
    private static final String CREATE_NOTICE = "create table Notice("
            +"id integer primary key autoincrement"
            +"NoticeTitle text"
            +"NoticeContent text"
            +"NoticeDate"
            +"NoticePath text)";

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCORE);
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_PLAN);
        db.execSQL(CREATE_NOTICE);
        db.execSQL(CREATE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
