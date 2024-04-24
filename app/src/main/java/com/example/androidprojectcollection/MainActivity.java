package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnFirst;
    Button btnSecond;
    Button btnFifth;
    Button btnSixth;
    Button btnSeventh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LayoutExercise.class);
                startActivity(intent);
            }
        });

        btnSecond = (Button) findViewById(R.id.btnSecond);
        btnSecond.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ButtonExercise.class);
                startActivity(intent);
            }
        });

        btnFifth = (Button) findViewById(R.id.btnFifth);
        btnFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConnectActivity.class);
                startActivity(intent);
            }
        });

        btnSixth = (Button) findViewById(R.id.btnSixth);
        btnSixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PassingIntentsExercise.class);
                startActivity(intent);
            }
        });

        btnSeventh = (Button) findViewById(R.id.btnSeventh);

        btnSeventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuExercise.class);
                startActivity(intent);
            }
        });
    }
}