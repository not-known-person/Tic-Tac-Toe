package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        layout = findViewById(R.id.secondary_layout);
    }

    public void Check(View view) {

        Button current = (Button) view;
        current.setText(totalMoves % 2 == 0 ? "X" : "O");
        ++totalMoves;
        TextView moves = findViewById(R.id.moves);
        moves.setText(String.valueOf(totalMoves));
        int row = totalMoves % 2 == 0 ? 0 : 1 ;
        pattern[row][(totalMoves % 2 == 0) ? (totalMoves / 2) - 1 : (totalMoves - 1) / 2] =
                layout.indexOfChild(view) + 1;
        if(totalMoves >= 4){
                int[] patterns = pattern[row];
                Log.d("patt" , Arrays.toString(patterns));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                boolean isMatched = Arrays.stream(positivePatterns)
                        .anyMatch(array -> Arrays.stream(array).allMatch(val -> Arrays.stream(patterns).anyMatch(x -> x == val)));
                if (isMatched) {
                    Toast.makeText(getApplicationContext(), (row == 0 ? "O" :
                            "X") + " won", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < layout.getChildCount(); i++) {
                        Button child = (Button) layout.getChildAt(i);
                        child.setText("");
                    }
                }

            }
        }
    }
}
