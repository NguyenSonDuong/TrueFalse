package com.kit501.truefalse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.kit501.truefalse.controller.event.EventEndGame;
import com.kit501.truefalse.controller.event.EventPlayGame;
import com.kit501.truefalse.model.ScoresModel;

public class MainPlayActivity extends AppCompatActivity {

    PlayFragment playFragment ;
    WecomeFragment wecomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main_play);
        playFragment = new PlayFragment(this,this);

        wecomeFragment = new WecomeFragment(this,this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        wecomeFragment.setEventPlayGame(new EventPlayGame() {
            @Override
            public void onPlay(ScoresModel scoresModel) {
                System.out.println("XINC ADALDJLAK");
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frMain, playFragment)
                        .commit();
            }
        });
        playFragment.setEventPlayGame(new EventPlayGame() {
            @Override
            public void onPlay(ScoresModel scoresModel) {
                System.out.println("XINC ADALDJLAK");
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frMain, playFragment)
                        .commit();
            }
        });
        playFragment.setEventEndGame(new EventEndGame() {
            @Override
            public void onEndGame(ScoresModel scoresModel) {
                System.out.println("XINC ADALDJLAK");
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frMain, wecomeFragment)
                        .commit();
            }
        });
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frMain, wecomeFragment)
                .commit();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.add(R.id.frMain, playFragment);
    }
}