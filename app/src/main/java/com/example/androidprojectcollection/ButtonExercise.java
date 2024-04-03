package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {
    Button btnClose, btnToast, btnChangeBG, btnChangeBtnBG, btnDisappear;
    int[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.CYAN};
    int colorLength = colors.length;

    int bgColorClickCounter = 0;
    int btnBgColorClickCounter = 0;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonExercise.this, CloseActivity.class);
                startActivity(intent);
            }
        });

        btnToast = (Button) findViewById(R.id.btnToast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonExercise.this, "This is a toast!", Toast.LENGTH_SHORT).show();
            }
        });

        btnChangeBG = (Button) findViewById(R.id.btnChangeBG);
        layout = (LinearLayout) findViewById(R.id.buttonExerciseLayout);
        btnChangeBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currIndex = bgColorClickCounter;
                layout.setBackgroundColor(colors[currIndex]);
                bgColorClickCounter++;
                if(bgColorClickCounter >= colorLength){
                    bgColorClickCounter = 0;
                }
            }
        });

        btnChangeBtnBG = (Button) findViewById(R.id.btnChangeBtnBG);
        btnChangeBtnBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currIndex = btnBgColorClickCounter;
                btnChangeBtnBG.setBackgroundColor(colors[currIndex]);
                btnBgColorClickCounter++;
                if(btnBgColorClickCounter >= colorLength){
                    btnBgColorClickCounter = 0;
                }
            }
        });

        btnDisappear = (Button) findViewById(R.id.btnDisappear);
        btnDisappear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDisappear.setVisibility(View.INVISIBLE);
            }
        });

    }
}