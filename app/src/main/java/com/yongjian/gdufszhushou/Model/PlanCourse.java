package com.yongjian.gdufszhushou.Model;

/**
 * Created by YONGJIAN on 2016/4/30 0030.
 */
public class PlanCourse {
    private  String cName;

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcCredit() {
        return cCredit;
    }

    public void setcCredit(String cCredit) {
        this.cCredit = cCredit;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    private String cType;
    private String cTime;
    private String cCredit;
}
