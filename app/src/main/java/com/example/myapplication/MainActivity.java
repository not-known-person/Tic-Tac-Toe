package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
Button btn1 , btn2 , btn3 , btn4 , btn5 , btn6 , btn7 , btn8 , btn9 ;
int[][] positivePatterns = {{1,2,3} , {4,5,6} , {7,8,9} , {1,4,7} , {2,5,8} , {3,6,9} , {1,5,9} , {3,5,7}};
GridLayout layout ;
int[][] pattern = new int[2][5];
int totalMoves = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadControls();
    }
    private void loadControls() {
        layout = findViewById(R.id.layout);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
    }
    public void Check(View view){

        Button current = (Button) view;
        current.setText(totalMoves%2 == 0 ? "X" : "O");
        totalMoves++;
        int childCount = layout.getChildCount();
        pattern[totalMoves%2 == 0 ? 0 : 1][totalMoves==1 ? totalMoves : Math.round(totalMoves/2)] = layout.indexOfChild(view)+1;
        Log.d("count" , Arrays.deepToString(pattern));
//        if(totalMoves > 4){
//            if(totalMoves%2==0){
//                int[] patterns = pattern[totalMoves];
//                boolean containsAll = Arrays.stream(positivePatterns)
//                        .anyMatch(row2 -> containsAllElements(pattern, row2));
//            }
//        }
    }
}
