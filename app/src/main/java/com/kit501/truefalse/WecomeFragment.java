package com.kit501.truefalse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kit501.truefalse.controller.event.EventPlayGame;
import com.kit501.truefalse.model.ScoresModel;

public class WecomeFragment extends Fragment {


    Context contextPareter;
    Activity activityPareter;

    EventPlayGame eventPlayGame;

    public WecomeFragment(Context contextPareter, Activity activityPareter) {
        this.contextPareter = contextPareter;
        this.activityPareter = activityPareter;
    }

    public WecomeFragment(int contentLayoutId, Context contextPareter, Activity activityPareter) {
        super(contentLayoutId);
        this.contextPareter = contextPareter;
        this.activityPareter = activityPareter;
    }

    public Context getContextPareter() {
        return contextPareter;
    }

    public void setContextPareter(Context contextPareter) {
        this.contextPareter = contextPareter;
    }

    public Activity getActivityPareter() {
        return activityPareter;
    }

    public void setActivityPareter(Activity activityPareter) {
        this.activityPareter = activityPareter;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(contextPareter).inflate(R.layout.fragment_wecome,container,false);
        ImageButton btnPlay = view.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eventPlayGame !=null)
                    eventPlayGame.onPlay(new ScoresModel());
            }
        });
        return view;
    }

    public EventPlayGame getEventPlayGame() {
        return eventPlayGame;
    }

    public void setEventPlayGame(EventPlayGame eventPlayGame) {
        this.eventPlayGame = eventPlayGame;
    }
}
