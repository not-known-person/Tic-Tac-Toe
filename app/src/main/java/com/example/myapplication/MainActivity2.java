package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        layout = findViewById(R.id.secondary_layout);
        XTimer = findViewById(R.id.X_Timer);
        OTimer = findViewById(R.id.O_Timer);
        setupUi();
//        setTimer(totalMoves % 2 == 0);
    }

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button current = (Button) view;
            ++totalMoves;
            boolean isZero = totalMoves % 2 == 0;
            current.setText(isZero ? "X" : "O");
            try {
                setTimer(isZero);
            }catch(Exception e){
                Log.d("error" ,String.valueOf(e));
            }
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
                        pattern = new int[2][5];
                        totalMoves = 0;
                        for (int i = 0; i < layout.getChildCount(); i++) {
                            Button child = (Button) layout.getChildAt(i);
                            child.setText("");
                        }
                    }
                }
            }
        }
    };

    private void setTimer(boolean position ) {
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
                autoSelect();
            }};
      countDownTimer.start();
    }

    private void autoSelect() {
        Random random = new Random();
        int index = random.nextInt(8 - 1 + 1) + 1;
        Log.d("index" , String.valueOf(index));
        Button child = (Button) layout.getChildAt(index);
        if(child.getText() == "") child.performClick(); else  autoSelect();
    }


    private void setupUi() {
            for (int i = 0; i < 9; i++) {
                Button btn = new Button(this);
                btn.setLayoutParams(new GridLayout.LayoutParams());
                btn.getLayoutParams().height = 300;
                btn.getLayoutParams().width = 300;
                btn.setTextSize(40);
                btn.setOnClickListener(buttonClickListener);
                layout.addView(btn);
            }
    }
}