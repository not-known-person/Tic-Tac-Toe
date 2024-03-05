package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    int[][] positivePatterns = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    GridLayout layout;
    int[][] pattern = new int[2][5];
    int totalMoves = 0;
    TextView XTimer ;
    TextView OTimer;
    CountDownTimer countDownTimer ;
    ArrayList<Integer> indexes = new ArrayList<>(Arrays.asList(0,1, 2, 3, 4, 5,6,7,8));
    MediaPlayer soundEffect ;
    MediaPlayer winEffect ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        layout = findViewById(R.id.secondary_layout);
        XTimer = findViewById(R.id.X_Timer);
        OTimer = findViewById(R.id.O_Timer);
        soundEffect = MediaPlayer.create( MainActivity2.this , R.raw.uiclick);
        winEffect = MediaPlayer.create(MainActivity2.this , R.raw.clickeffect);
        setupUi();
    }

        View.OnClickListener buttonClickListener = new View.OnClickListener() {

            @Override
        public void onClick(View view) {
            soundEffect.start();
            Button current = (Button) view;
            ++totalMoves;
            indexes.remove(indexes.indexOf(layout.indexOfChild(view)));
            Log.d("indexes-new" , String.valueOf(indexes));
            boolean isZero = totalMoves % 2 == 0;
            current.setText(isZero ? "X" : "O");
                int row = isZero ? 0 : 1 ;
                pattern[row][isZero ? (totalMoves / 2) - 1 : (totalMoves - 1) / 2] =
                        layout.indexOfChild(view) + 1;
            if(totalMoves >= 4){
                int[] patterns = pattern[row];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    boolean isMatched = Arrays.stream(positivePatterns)
                            .anyMatch(array -> Arrays.stream(array).allMatch(val -> Arrays.stream(patterns).anyMatch(x -> x == val)));
                    if (isMatched) {
                        Toast.makeText(getApplicationContext(), (row == 0 ? "X" :
                                "O") + " won", Toast.LENGTH_SHORT).show();
                        winEffect.start();
                        resetAllIndex();
                        return;
                    }
                    if (indexes.isEmpty()){
                        resetAllIndex();
                        Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            setTimer(isZero , indexes);
        }
    };
private void resetAllIndex(){
    pattern = new int[2][5];
    indexes = new ArrayList<>(Arrays.asList(0,1, 2, 3, 4, 5,6,7,8));
    totalMoves = 0;
    for (int i = 0; i < layout.getChildCount(); i++) {
        Button child = (Button) layout.getChildAt(i);
        child.setText("");
    }
}
    private void setTimer(boolean position , ArrayList<Integer> indexes ) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
       countDownTimer =  new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timerText = String.format("%02d:%02d", millisUntilFinished / 1000 / 60, millisUntilFinished / 1000 % 60);
                if (position) {
                    XTimer.setText("");
                    OTimer.setText(timerText);
                } else {
                    OTimer.setText("");
                    XTimer.setText(timerText);
                }
            }
            @Override
            public void onFinish() {
                autoSelect(indexes);
            }};
      countDownTimer.start();
    }

    private void autoSelect( ArrayList<Integer> indexes) {
        Random random = new Random();
        int index = random.nextInt(indexes.size()-1);
        Log.d("auto", String.valueOf(indexes));
        Log.d("index" , String.valueOf(index));
        Button child = (Button) layout.getChildAt(indexes.get(index));
        child.performClick();
    }


    private void setupUi() {
            for (int i = 0; i < 9; i++) {
                Button btn = new Button(this);
                btn.setLayoutParams(new GridLayout.LayoutParams());
                btn.getLayoutParams().height = 300;
                btn.getLayoutParams().width = 300;
                btn.setTextSize(40);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btn.setTextColor(getColor(R.color.white));
                }
//                Typeface thinTypeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);
//                btn.setTypeface(thinTypeface);
                btn.setBackgroundResource(R.drawable.btn_bg);
                btn.setOnClickListener(buttonClickListener);
                layout.addView(btn);
            }
    }
}