package com.yongjian.gdufszhushou.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yongjian.gdufszhushou.Model.Score;
import com.yongjian.gdufszhushou.Util.HandleResponseUtil;

/**
 * Created by YONGJIAN on 2016/3/16 0016.
 */
public class Db {

    public static final String DB_NAME = "school_info.db";
    public static final int VERSION = 1;
    private static Db db;
    private SQLiteDatabase sqlDb;

    private Db(Context context) {
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context,DB_NAME,null,VERSION);
        sqlDb = dbOpenHelper.getWritableDatabase();
    }

    public synchronized static  Db getInstance(Context context){
        if (db ==null){
            db = new Db(context);
        }
        return  db;
    }

    public void saveScore(Score score){
        if (score !=null){
            ContentValues values = new ContentValues();
            values.put("ScoreName",score.getScoreName());
            values.put("ScoreCredit",score.getCredit());
            values.put("ScorePoint",score.getScorePoint());
            values.put("ScoreType",score.getType());
            values.put("ScoreGrade",score.getScore());
            sqlDb.insert("Score",null,values);
        }
    }
    public boolean loadScore(){
        Cursor cursor = sqlDb.query("Score",null,null,null,null,null,null);
        int flag = 0 ;
        HandleResponseUtil.scores.clear();
        if (cursor.moveToFirst()){
            do{
                flag = 1;
                Score score = new Score();
                score.setScoreName(cursor.getString(cursor.getColumnIndex("ScoreName")));
                score.setScore(cursor.getString(cursor.getColumnIndex("ScoreGrade")));
                score.setCredit(cursor.getString(cursor.getColumnIndex("ScoreCredit")));
                score.setScorePoint(cursor.getString(cursor.getColumnIndex("ScorePoint")));
                score.setType(cursor.getString(cursor.getColumnIndex("ScoreType")));
                HandleResponseUtil.scores.add(score);

            }while (cursor.moveToNext());
            return true;
        }
        return  false;
    }

}
