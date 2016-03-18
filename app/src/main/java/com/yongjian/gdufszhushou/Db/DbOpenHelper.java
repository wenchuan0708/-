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


    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
