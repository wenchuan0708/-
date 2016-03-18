package com.yongjian.gdufszhushou.Model;




/**
 * Created by YONGJIAN on 2016/3/9 0009.
 */
public class Score {
    private String ScoreName;  //课程名
    private String Credit; // 学分
    private String Type;  //课程性质

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScorePoint() {
        return ScorePoint;
    }

    public void setScorePoint(String scorePoint) {
        ScorePoint = scorePoint;
    }

    public String getScoreName() {
        return ScoreName;
    }

    public void setScoreName(String scoreName) {
        ScoreName = scoreName;
    }

    private String score;  //分数
    private String ScorePoint; //绩点

}
