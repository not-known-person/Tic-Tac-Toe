package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int[][] positivePatterns = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    GridLayout layout;
    int[][] pattern = new int[2][5];
    int totalMoves = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout);
    }

    public void Check(View view) {

        Button current = (Button) view;
        current.setText(totalMoves % 2 == 0 ? "X" : "O");
        ++totalMoves;
        pattern[totalMoves % 2 == 0 ? 0 : 1][(totalMoves % 2 == 0) ? (totalMoves / 2) - 1 : (totalMoves - 1) / 2] = layout.indexOfChild(view) + 1;
        if(totalMoves >= 4){
                int[] patterns = pattern[totalMoves % 2 == 0 ? 0 : 1];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                boolean containsAllElements = Arrays.stream(positivePatterns)
                        .anyMatch(array -> Arrays.stream(patterns).allMatch(val -> Arrays.stream(array).anyMatch(x -> x == val)));
                Log.d("res",String.valueOf(containsAllElements));
            }
        }
    }
}
