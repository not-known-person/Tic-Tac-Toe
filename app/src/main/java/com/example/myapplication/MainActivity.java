package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
Spinner playersSpinner ;
Spinner tokensSpinner ;
ArrayList<Integer> players = new ArrayList<>(Arrays.asList(1,2));
ArrayList<String> tokens = new ArrayList<>(Arrays.asList("X","O"));
Button btn;
EditText timeInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playersSpinner = findViewById(R.id.playersSpinner);
        tokensSpinner = findViewById(R.id.tokensSpinner);
        timeInput = findViewById(R.id.time);
        btn = findViewById(R.id.start);
        ArrayAdapter<Integer> playersAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, players);
        playersSpinner.setAdapter(playersAdapter);
        ArrayAdapter<String> tokensAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, tokens);
        tokensSpinner.setAdapter(tokensAdapter);
        playersSpinner.setSelection(0);
        tokensSpinner.setSelection(0);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("players",  String.valueOf(playersSpinner.getSelectedItem()));
                intent.putExtra("token", String.valueOf(tokensSpinner.getSelectedItem()));
                intent.putExtra("timeLimit",   String.valueOf(timeInput.getText()));
//                Log.d("lol" , String.valueOf(timeInput.getText()));
                startActivity(intent);
            }
        });
    }







}
