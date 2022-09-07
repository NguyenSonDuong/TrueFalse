package com.kit501.truefalse.model;

import com.kit501.truefalse.controller.event.EventNumberChange;

public class ScoresModel {

    public static String SHARED_PREFERENCES_NAME = "scores";
    private int scores;
    private int num1;
    private int num2;
    private int num3;
    private PT pt;
    private int lever;

    public ScoresModel() {
    }

    public ScoresModel(int scores, int num1, int num2, int num3, PT pt, int lever) {
        this.scores = scores;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.pt = pt;
        this.lever = lever;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public PT getPt() {
        return pt;
    }

    public void setPt(String pt2) {
        PT pt = PT.CONG;
        if(pt2.equals(PT.CONG.toString()))
            pt = PT.CONG;
        if(pt2.equals(PT.TRU.toString()))
            pt = PT.TRU;
        if(pt2.equals(PT.NHAN.toString()))
            pt = PT.NHAN;
        if(pt2.equals(PT.CHIA.toString()))
            pt = PT.CHIA;
        this.pt = pt;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public void reset(EventNumberChange eventNumberChange){
        num1 = 0;
        num2 = 0;
        num3 = 0;
        scores = 0;
        lever = 0;
        eventNumberChange.OnChange(KeySaveModel.KEY_PT , 0);
    }
    public boolean isTrueDapAn(){
        return (num1 + num2) == num3;
    }
}


