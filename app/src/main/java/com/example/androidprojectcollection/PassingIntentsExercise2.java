package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PassingIntentsExercise2 extends AppCompatActivity {
    private TextView displayFirstName;
    private TextView displayLastName;
    private TextView displayGender;
    private TextView displayBirthDate;
    private TextView displayHomeAddress;
    private TextView displayPhoneNumber;
    private TextView displayEmailAddress;
    private TextView displayProgram;
    private TextView displayYearLevel;
    private TextView displayStudentID;
    private TextView displayMaritalStatus;

    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);
        initializeView();
    }

    private void initializeFields(){
        displayFirstName = (TextView) findViewById(R.id.displayFirstName);
        displayLastName = (TextView) findViewById(R.id.displayLastName);
        displayGender = (TextView) findViewById(R.id.displayGender);
        displayBirthDate = (TextView) findViewById(R.id.displayBirthDate);
        displayHomeAddress = (TextView) findViewById(R.id.displayHomeAddress);
        displayPhoneNumber = (TextView) findViewById(R.id.displayPhoneNumber);
        displayEmailAddress = (TextView) findViewById(R.id.displayEmailAddress);
        displayProgram = (TextView) findViewById(R.id.displayProgram);
        displayYearLevel = (TextView) findViewById(R.id.displayYearLevel);
        displayStudentID = (TextView) findViewById(R.id.displayStudentID);
        displayMaritalStatus = (TextView) findViewById(R.id.displayMaritalStatus);

        btnReturn = (Button) findViewById(R.id.btnReturn);
    }

    private void initializeView(){
        initializeFields();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        displayFirstName.setText(intent.getStringExtra("fname_key"));
        displayLastName.setText(intent.getStringExtra("lname_key"));
        displayGender.setText(intent.getStringExtra("gender_key"));
        displayBirthDate.setText(intent.getStringExtra("bdate_key"));
        displayHomeAddress.setText(intent.getStringExtra("home_add_key"));
        displayPhoneNumber.setText(intent.getStringExtra("pnum_key"));
        displayEmailAddress.setText(intent.getStringExtra("email_add_key"));
        displayProgram.setText(intent.getStringExtra("program_key"));
        displayYearLevel.setText(intent.getStringExtra("yearlvl_key"));
        displayStudentID.setText(intent.getStringExtra("studid_key"));
        displayMaritalStatus.setText(intent.getStringExtra("status_key"));
    }
}