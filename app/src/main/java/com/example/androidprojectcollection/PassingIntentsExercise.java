package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class PassingIntentsExercise extends AppCompatActivity {
    private Button btnSubmit;
    private Button btnClearFields;

    private EditText firstName;
    private EditText lastName;
    private EditText birthDate;
    private EditText homeAddress;
    private EditText phoneNumber;
    private EditText emailAddress;
    private EditText studentID;

    private RadioButton rMale;
    private RadioButton rFemale;
    private RadioButton rOthers;

    private Spinner dropdownYearLvl;
    private Spinner dropdownProgram;
    private Spinner dropdownMaritalStatus;

    private String[] programs = {"Select Program", "BS in Computer Science", "BS in Information Technology", "BS in Computer Engineering"};
    private String[] yearLvls = {"Select Year Level", "First Year", "Second Year", "Third Year", "Fourth Year"};
    private String[] maritalStatusList = {"Select Status" , "Single", "Married", "Separated"};

    private ArrayAdapter programAdapter;
    private ArrayAdapter yearLevelAdapter;
    private ArrayAdapter maritalStatusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);
        initializeView();
    }

    private void initializeFields(){
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnClearFields = (Button) findViewById(R.id.btnClearFields);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        birthDate = (EditText) findViewById(R.id.birthDate);
        homeAddress = (EditText) findViewById(R.id.homeAddress);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        studentID = (EditText) findViewById(R.id.studentID);

        rMale = (RadioButton) findViewById(R.id.rMale);
        rFemale = (RadioButton) findViewById(R.id.rFemale);
        rOthers = (RadioButton) findViewById(R.id.rOthers);

        dropdownProgram = (Spinner) findViewById(R.id.dropdownProgram);
        dropdownYearLvl = (Spinner) findViewById(R.id.dropdownYearLvl);
        dropdownMaritalStatus = (Spinner) findViewById(R.id.dropdownMaritalStatus);

        programAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, programs);
        yearLevelAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, yearLvls);
        maritalStatusAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, maritalStatusList);

        dropdownProgram.setAdapter(programAdapter);
        dropdownYearLvl.setAdapter(yearLevelAdapter);
        dropdownMaritalStatus.setAdapter(maritalStatusAdapter);
    }
    private void initializeView(){
        initializeFields();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();

                String gender;
                if(rMale.isChecked()){
                    gender = "Male";
                }else if(rFemale.isChecked()){
                    gender = "Female";
                }else if(rOthers.isChecked()) {
                    gender = "Others";
                }else {
                    gender = "Unknown";
                }

                String bDate = birthDate.getText().toString();
                String pNumber = phoneNumber.getText().toString();
                String emailAdd = emailAddress.getText().toString();
                String homeAdd = homeAddress.getText().toString();
                String program = dropdownProgram.getSelectedItem().toString();
                String yearLevel = dropdownYearLvl.getSelectedItem().toString();
                String maritalStatus = dropdownMaritalStatus.getSelectedItem().toString();
                String studid = studentID.getText().toString();

                Intent intent = new Intent(PassingIntentsExercise.this, PassingIntentsExercise2.class);

                intent.putExtra("fname_key", fname);
                intent.putExtra("gender_key", gender);
                intent.putExtra("pnum_key", pNumber);
                intent.putExtra("lname_key", lname);
                intent.putExtra("bdate_key", bDate);
                intent.putExtra("email_add_key", emailAdd);
                intent.putExtra("home_add_key", homeAdd);
                intent.putExtra("program_key", program);
                intent.putExtra("yearlvl_key", yearLevel);
                intent.putExtra("studid_key", studid);
                intent.putExtra("status_key", maritalStatus);

                startActivity(intent);
                Toast.makeText(PassingIntentsExercise.this, "Form Submitted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnClearFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName.setText("");
                lastName.setText("");
                birthDate.setText("");
                homeAddress.setText("");
                phoneNumber.setText("");
                emailAddress.setText("");
                rMale.setChecked(false);
                rFemale.setChecked(false);
                rOthers.setChecked(false);
                studentID.setText("");
                dropdownProgram.setSelection(0);
                dropdownYearLvl.setSelection(0);
                dropdownMaritalStatus.setSelection(0);
            }
        });
    }
}