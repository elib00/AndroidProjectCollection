package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Random;

public class MenuExercise extends AppCompatActivity {
    private int[] buttonColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};
    private int[] textColors = {Color.BLACK, Color.WHITE};
    private String[] directions = {"left", "right", "down", "up"};

    private String[] fruits = {"Apple", "Blueberry", "Guava", "Mango", "Grape"};
    Button btnTransformer;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercise);

        btnTransformer = (Button) findViewById(R.id.btnTransformer);
        btnTransformer.setBackgroundColor(Color.YELLOW);
        btnTransformer.setTextColor(Color.BLACK);
        random = new Random();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choice_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.mItemChange){
            Toast.makeText(this, "Edit Object Item is clicked", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.mItemReset){
            btnTransformer.setBackgroundColor(Color.YELLOW);
            btnTransformer.setTextColor(Color.BLACK);
            btnTransformer.setTranslationX(0);
            btnTransformer.setTranslationY(0);
            btnTransformer.setText("I am a fruit");
            btnTransformer.setScaleX(1);
            btnTransformer.setScaleY(1);
            btnTransformer.setRotation(0);
            Toast.makeText(this, "Reset Object Item is clicked", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.mItemExit){
            finish();
        }else if(item.getItemId() == R.id.mItemBGChange){
            int buttonColor = buttonColors[random.nextInt(buttonColors.length)];
            int textColor = textColors[random.nextInt(textColors.length)];
            btnTransformer.setBackgroundColor(buttonColor);
            btnTransformer.setTextColor(textColor);
        }else if(item.getItemId() == R.id.mItemMove){
            String dir = directions[random.nextInt(directions.length)];
            if(dir.equals("right")){
                btnTransformer.setTranslationX(random.nextInt(300));
            }else if(dir.equals("left")){
                btnTransformer.setTranslationX(random.nextInt(300) * -1);
            }else if(dir.equals("up")){
                btnTransformer.setTranslationY(random.nextInt(300));
            }else{
                btnTransformer.setTranslationY(random.nextInt(300) * -1);
            }
            Toast.makeText(this, "Direction Change Object Item is clicked", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.mItemTextChange){
            String fruit = fruits[random.nextInt(fruits.length)];
            btnTransformer.setText(fruit);
            Toast.makeText(this, "Text Change Object Item is clicked", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.mItemSizeChange){
            btnTransformer.setScaleX(random.nextInt(1500) / 1000.0f  + 0.5f);
            btnTransformer.setScaleY(random.nextInt(1500) / 1000.0f  + 0.5f);
            Toast.makeText(this, "Size Change Object Item is clicked", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.mItemRotationChange){
            btnTransformer.setRotation((float) random.nextInt(360));
            Toast.makeText(this, "Rotation Change Object Item is clicked", Toast.LENGTH_SHORT).show();
        }


        return true;
    }
}