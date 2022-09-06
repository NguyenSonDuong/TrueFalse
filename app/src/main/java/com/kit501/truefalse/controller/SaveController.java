package com.kit501.truefalse.controller;

import android.content.Context;
import android.content.SharedPreferences;
import com.kit501.truefalse.model.KeySaveModel;
import com.kit501.truefalse.model.ScoresModel;

public class SaveController {
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SaveController(Context context) {        this.context = context;
    }

    public boolean WriteScores(ScoresModel scoresModel){
        try{
            SharedPreferences sharedPreferences = context.getSharedPreferences(ScoresModel.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KeySaveModel.SCORES.toString(),scoresModel.getScores());
            editor.putString(KeySaveModel.KEY_PT.toString(),scoresModel.getPt().toString());
            editor.putInt(KeySaveModel.NUM1.toString(),scoresModel.getNum1());
            editor.putInt(KeySaveModel.NUM2.toString(),scoresModel.getNum2());
            editor.putInt(KeySaveModel.NUM3.toString(),scoresModel.getNum3());
            editor.putInt(KeySaveModel.LEVER.toString(),scoresModel.getLever());
        }catch (Exception exception){
            System.out.println("WriteScores: "+exception.getMessage());
            return false;
        }
        return  true;
    }
    public ScoresModel ReadScores(){
        try{
            SharedPreferences sharedPreferences = context.getSharedPreferences(ScoresModel.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            ScoresModel scoresModel = new ScoresModel();
            scoresModel.setScores(sharedPreferences.getInt(KeySaveModel.SCORES.toString(),0));
            scoresModel.setScores(sharedPreferences.getInt(KeySaveModel.NUM1.toString(),0));
            scoresModel.setScores(sharedPreferences.getInt(KeySaveModel.NUM2.toString(),0));
            scoresModel.setScores(sharedPreferences.getInt(KeySaveModel.NUM3.toString(),0));
            scoresModel.setScores(sharedPreferences.getInt(KeySaveModel.LEVER.toString(),0));
            scoresModel.setPt(sharedPreferences.getString(KeySaveModel.KEY_PT.toString(),"CONG"));
            return scoresModel;
        }catch (Exception exception){
            System.out.println("WriteScores: "+exception.getMessage());
            return new ScoresModel();
        }
    }
}
