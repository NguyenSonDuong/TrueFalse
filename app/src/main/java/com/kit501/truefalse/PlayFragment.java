package com.kit501.truefalse;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.Fragment;
import com.kit501.truefalse.controller.ActionController;
import com.kit501.truefalse.controller.event.EventEndGame;
import com.kit501.truefalse.controller.event.EventNumberChange;
import com.kit501.truefalse.controller.event.EventPlayGame;
import com.kit501.truefalse.model.KeySaveModel;

import java.util.Timer;
import java.util.TimerTask;

public class PlayFragment extends Fragment {

    Context context;

    EventEndGame eventEndGame;

    public EventPlayGame getEventPlayGame() {
        return eventPlayGame;
    }

    public void setEventPlayGame(EventPlayGame eventPlayGame) {
        this.eventPlayGame = eventPlayGame;
    }

    EventPlayGame eventPlayGame;

    public EventEndGame getEventEndGame() {
        return eventEndGame;
    }

    public void setEventEndGame(EventEndGame eventEndGame) {
        this.eventEndGame = eventEndGame;
    }

    boolean isStop = false;
    Timer timer;
    Timer timer2;
    Activity activity;

    public PlayFragment(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public PlayFragment(int contentLayoutId, Context context, Activity activity) {
        super(contentLayoutId);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_play,container,false);
        ActionController actionController = new ActionController(getContext());
        TextView tvNum1 = view.findViewById(R.id.tvNum1);
        TextView tvNum2 = view.findViewById(R.id.tvNum2);
        TextView tvNum3 = view.findViewById(R.id.tvNum3);
        TextView tvScores = view.findViewById(R.id.tvScores);

        ImageButton btnTrue = view.findViewById(R.id.btnTrue);
        ImageButton btnFalse = view.findViewById(R.id.btnFalse);

        System.out.printf(tvNum1.getText().toString());
        actionController.setEventNumberChange(new EventNumberChange() {
            @Override
            public void OnChange(KeySaveModel key, int number) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (key == KeySaveModel.NUM1)
                            tvNum1.setText(number + "");
                        if (key == KeySaveModel.NUM2)
                            tvNum2.setText(number + "");
                        if (key == KeySaveModel.NUM3)
                            tvNum3.setText(number + "");
                        if (key == KeySaveModel.SCORES)
                            tvScores.setText(number + "");
                        if(key == KeySaveModel.KEY_PT)
                        {
                            tvNum1.setText(number + "");
                            tvNum2.setText(number + "");
                            tvScores.setText(number + "");
                            tvNum3.setText(number + "");
                        }
                    }
                });

            }
        });
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionController.setDapAnClick(true);
                Play(actionController,context);
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionController.setDapAnClick(false);
                Play(actionController,context);
            }
        });

        Play(actionController,context);
        return view;
    }
    public void Play(ActionController actionController, Context context) {
        actionController.Random();
        if (timer != null)
            try {
                timer.cancel();
            } catch (Exception ex) {

            }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                actionController.timeOut();
                activity.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        DialogLost cdd=new DialogLost(context);
                        cdd.setScoresModel(actionController.getScoresModel());
                        cdd.setEventEndGame(eventEndGame);
                        cdd.setEventPlayGame(eventPlayGame);
                        cdd.show();
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(cdd.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        cdd.show();
                        cdd.getWindow().setAttributes(lp);

//                        eventEndGame.onEndGame(actionController.getScoresModel());
//                        new AlertDialog.Builder(context)
//                                .setTitle("Delete entry")
//                                .setMessage("Are you sure you want to delete this entry?")
//
//                                // Specifying a listener allows you to take an action before dismissing the dialog.
//                                // The dialog is automatically dismissed when a dialog button is clicked.
//                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // Continue with delete operation
//                                    }
//                                })
//
//                                // A null listener allows the button to dismiss the dialog and take no further action.
//                                .setNegativeButton(android.R.string.no, null)
//                                .setIcon(android.R.drawable.ic_dialog_alert)
//                                .show();
                    }
                });
            }
        }, 3000);
    }
}