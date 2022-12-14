package com.kit501.truefalse;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kit501.truefalse.controller.ActionController;
import com.kit501.truefalse.controller.event.EventNumberChange;
import com.kit501.truefalse.model.KeySaveModel;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    boolean isStop = false;
    Timer timer;
    Timer timer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);

        ActionController actionController = new ActionController(this);

        TextView tvNum1 = this.findViewById(R.id.tvNum1);
        TextView tvNum2 = this.findViewById(R.id.tvNum2);
        TextView tvNum3 = this.findViewById(R.id.tvNum3);
        TextView tvScores = this.findViewById(R.id.tvScores);

        ImageButton btnTrue = this.findViewById(R.id.btnTrue);
        ImageButton btnFalse = this.findViewById(R.id.btnFalse);


        actionController.setEventNumberChange(new EventNumberChange() {
            @Override
            public void OnChange(KeySaveModel key, int number) {
                runOnUiThread(new Runnable() {
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
        Context context = this;
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

        Play(actionController,this);

//        Handler handler = new Handler();
//        while (true) {
//            handler.postAtTime(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            }, 10);
//        }
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
                runOnUiThread(new TimerTask() {
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