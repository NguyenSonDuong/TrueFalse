package com.kit501.truefalse.controller;

import android.content.Context;
import android.os.Handler;
import com.kit501.truefalse.controller.event.EventNumberChange;
import com.kit501.truefalse.model.KeySaveModel;
import com.kit501.truefalse.model.ScoresModel;

import java.util.Random;

public class ActionController {

    private EventNumberChange eventNumberChange;
    private Context context;
    private int startNumber = 0;
    private int endNumber = 20;

    private int dapAn = 0;

    private Random generator = new Random();
    private ScoresModel scoresModel;

    public int getDapAn() {
        return dapAn;
    }

    public void setDapAn(int dapAn) {
        this.dapAn = dapAn;
    }
    public ScoresModel getScoresModel() {
        return scoresModel;
    }

    public EventNumberChange getEventNumberChange() {
        return eventNumberChange;
    }

    public void setEventNumberChange(EventNumberChange eventNumberChange) {
        this.eventNumberChange = eventNumberChange;
    }

    public void setScoresModel(ScoresModel scoresModel) {
        this.scoresModel = scoresModel;
    }

    public ActionController(Context context, ScoresModel scoresModel) {
        this.context = context;
        this.scoresModel = scoresModel;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ActionController(Context context) {
        this.scoresModel = new ScoresModel();
        this.context = context;
    }

    public void Random() {
        int[] dapan = new int[11];

        scoresModel.setNum1(generator.nextInt((endNumber - startNumber) + 1) + startNumber);
        if (eventNumberChange != null)
            eventNumberChange.OnChange(KeySaveModel.NUM1, scoresModel.getNum1());
        scoresModel.setNum2(generator.nextInt((endNumber - startNumber) + 1) + startNumber);
        if (eventNumberChange != null)
            eventNumberChange.OnChange(KeySaveModel.NUM2, scoresModel.getNum2());
        int startDapAn = scoresModel.getNum1() + scoresModel.getNum2();
        for (int i = 0; i < 10; i++) {
            if (i <= 5)
                dapan[i] = startDapAn;
            if (i > 5)
                dapan[i] = generator.nextInt(((startDapAn + 5) - (startDapAn - 5)) + 1) + (startDapAn - 5);
        }
        scoresModel.setNum3(dapan[generator.nextInt(10)]);
        this.dapAn = scoresModel.getNum3();
        if (eventNumberChange != null)
            eventNumberChange.OnChange(KeySaveModel.NUM3, scoresModel.getNum3());
    }
}
