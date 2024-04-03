package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConnectActivity extends AppCompatActivity {
    private int[] colors = {Color.RED, Color.BLACK};
    private int[][] gameGrid;
    private Button[][] tiles;
    private int[][] btnColors;
    public static final int row = 5;
    public static final int col = 5;
    private boolean isBlack;
    private TextView currPlayer;
    private TextView winText;
    private Button btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        initialize();
        handleLogic();
    }

    private void createGameGrid(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                gameGrid[i][j] = -1;
            }
        }
    }

    private void initialize(){
        tiles = new Button[row][col];
        gameGrid = new int[row][col];
        btnColors = new int[row][col];

        createGameGrid();

        currPlayer = findViewById(R.id.currentPlayerText);
        winText = findViewById(R.id.winText);
        btnReset = (Button) findViewById(R.id.btnReset);
        isBlack = true;
    }

    private void reset(){
        gameGrid = new int[row][col];
        setDefaultTileColors();
        createGameGrid();

        isBlack = true;
        currPlayer.setText("Black's Turn");
        currPlayer.setTextColor(colors[1]);
        winText.setText("");
    }

    private void setDefaultTileColors(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Button tile = tiles[i][j];
                tile.setBackgroundColor(Color.WHITE);
                btnColors[i][j] = Color.WHITE;
            }
        }
    }

    private boolean check(int r, int c){
        int referenceColor = btnColors[r][c];

        //for horizontal left direction
        int left = c - 1;
        int leftCount = 1;
        //only check twice
        for(int i = 1; i <= 2; i++){
            try{
                int currColor = btnColors[r][left];
                if(currColor == referenceColor){
                    leftCount++;
                }
                left--;
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("Just pass");
                break;
            }
        }

        System.out.println(leftCount);

        if(leftCount == 3) return true;
//
//        //for horizontal right direction
//        int right = c + 1;
//        int rightCount = 1;
//
//        //check only twice
//        for(int i = 1; i <= 2; i++){
//            try{
//                int currColor = btnColors[r][right];
//                if(currColor == referenceColor){
//                    rightCount++;
//                }
//                right++;
//            }catch(ArrayIndexOutOfBoundsException e){
//                e.printStackTrace();
//                System.out.println("Just pass");
//                break;
//            }
//        }
//
//        if(rightCount == 3) return true;


        return false;
    }

    private void handleLogic(){
        //get all the tiles and store them in a 2D array of buttons
        TableLayout parent = (TableLayout) findViewById(R.id.tilesContainer);
        for(int i = 0; i < parent.getChildCount(); i++) {
            TableRow childRow = (TableRow) parent.getChildAt(i);
            for (int j = 0; j < childRow.getChildCount(); j++) {
                Button tile = (Button) childRow.getChildAt(j);
                tiles[i][j] = tile;
            }
        }

        // set all tiles to white
        setDefaultTileColors();

        if(isBlack){
            currPlayer.setText("Black's Turn");
            currPlayer.setTextColor(colors[1]);
        }else{
            currPlayer.setText("Red's Turn");
            currPlayer.setTextColor(colors[0]);
        }

        //only the top row is clickable
        for(int i = 0; i < col; i++){
            Button tile = tiles[0][i];

            int currentCol = i;
            tile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int j = row - 1; j >= 0; j--){
                        if(gameGrid[j][currentCol] != -1){
                            continue;
                        }else{
                            gameGrid[j][currentCol] = 1;
                            boolean gameEnds;
                            if(isBlack){
                                gameEnds = check(j, currentCol);
//                                gameEnds = false;
                                tiles[j][currentCol].setBackgroundColor(colors[1]);
                                btnColors[j][currentCol] = colors[1];
                                currPlayer.setText("Red's Turn");
                                currPlayer.setTextColor(colors[0]);
                                isBlack = false;

                                if(gameEnds){
                                    winText.setText("BLACK WON!!!");
                                    winText.setTextColor(colors[1]);
                                }

                            }else{
                                gameEnds = check(j, currentCol);
//                                gameEnds = false;

                                if(gameEnds){
                                    winText.setText("RED WON!!!");
                                    winText.setTextColor(colors[0]);
                                }else{
                                    tiles[j][currentCol].setBackgroundColor(colors[0]);
                                    btnColors[j][currentCol] = colors[0];
                                    currPlayer.setText("Black's Turn");
                                    currPlayer.setTextColor(colors[1]);
                                    isBlack = true;
                                }
                            }
                            break;
                        }
                    }
                }
            });
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });



    }
}