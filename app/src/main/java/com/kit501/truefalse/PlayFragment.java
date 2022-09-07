package com.kit501.truefalse;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;

import com.kit501.truefalse.controller.ActionController;
import com.kit501.truefalse.controller.event.EventNumberChange;
import com.kit501.truefalse.model.KeySaveModel;

import java.util.Timer;
import java.util.TimerTask;

public class PlayFragment extends Fragment {

    boolean isStop = false;
    Timer timer;
    Timer timer2;
    Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionController actionController = new ActionController(container.getContext());

        TextView tvNum1 = getView().findViewById(R.id.tvNum1);
        TextView tvNum2 = getView().findViewById(R.id.tvNum2);
        TextView tvNum3 = getView().findViewById(R.id.tvNum3);
        TextView tvScores = getView().findViewById(R.id.tvScores);

        ImageButton btnTrue = getView().findViewById(R.id.btnTrue);
        ImageButton btnFalse = getView().findViewById(R.id.btnFalse);

        activity = this.getActivity();

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
        Context context = container.getContext();
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

        Play(actionController,container.getContext());
        return inflater.inflate(R.layout.fragment_play, container, false);
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
                        new AlertDialog.Builder(context)
                                .setTitle("Delete entry")
                                .setMessage("Are you sure you want to delete this entry?")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });
            }
        }, 3000);
    }
}