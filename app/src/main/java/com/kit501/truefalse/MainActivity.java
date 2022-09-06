package com.kit501.truefalse;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.kit501.truefalse.controller.ActionController;
import com.kit501.truefalse.controller.event.EventNumberChange;
import com.kit501.truefalse.model.KeySaveModel;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

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

        actionController.setEventNumberChange(new EventNumberChange() {
            @Override
            public void OnChange(KeySaveModel key, int number) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (key == KeySaveModel.NUM1)
                            tvNum1.setText(number+"");
                        if (key == KeySaveModel.NUM2)
                            tvNum2.setText(number+"");
                        if (key == KeySaveModel.NUM3)
                            tvNum3.setText(number+"");
                    }
                });

            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                actionController.Random();
            }
        }, 5000,5000);
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


}