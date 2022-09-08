package com.kit501.truefalse;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kit501.truefalse.controller.event.EventEndGame;
import com.kit501.truefalse.controller.event.EventPlayGame;
import com.kit501.truefalse.model.ScoresModel;

public class DialogLost extends Dialog {
    ScoresModel scoresModel;

    EventPlayGame eventPlayGame;
    EventEndGame eventEndGame;

    public ScoresModel getScoresModel() {
        return scoresModel;
    }

    public void setScoresModel(ScoresModel scoresModel) {
        this.scoresModel = scoresModel;
    }

    public EventPlayGame getEventPlayGame() {
        return eventPlayGame;
    }

    public void setEventPlayGame(EventPlayGame eventPlayGame) {
        this.eventPlayGame = eventPlayGame;
    }

    public EventEndGame getEventEndGame() {
        return eventEndGame;
    }

    public void setEventEndGame(EventEndGame eventEndGame) {
        this.eventEndGame = eventEndGame;
    }

    public DialogLost(@NonNull Context context, ScoresModel scoresModel, EventPlayGame eventPlayGame, EventEndGame eventEndGame) {
        super(context);
        this.scoresModel = scoresModel;
        this.eventPlayGame = eventPlayGame;
        this.eventEndGame = eventEndGame;
    }

    public DialogLost(@NonNull Context context, int themeResId, ScoresModel scoresModel, EventPlayGame eventPlayGame, EventEndGame eventEndGame) {
        super(context, themeResId);
        this.scoresModel = scoresModel;
        this.eventPlayGame = eventPlayGame;
        this.eventEndGame = eventEndGame;
    }

    public DialogLost(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, ScoresModel scoresModel, EventPlayGame eventPlayGame, EventEndGame eventEndGame) {
        super(context, cancelable, cancelListener);
        this.scoresModel = scoresModel;
        this.eventPlayGame = eventPlayGame;
        this.eventEndGame = eventEndGame;
    }



    public DialogLost(@NonNull Context context) {
        super(context);
    }

    public DialogLost(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogLost(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    public void Disshow(){
        this.hide();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_notification_game);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnReplay = findViewById(R.id.btnReplay);
        TextView tvPoint = findViewById(R.id.tvPoint);
        tvPoint.setText(scoresModel.getScores()+"");
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventEndGame.onEndGame(scoresModel);
                Disshow();
            }
        });
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventPlayGame.onPlay(scoresModel);
                Disshow();
            }
        });
    }


}
