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
    private final int[] colors = {Color.RED, Color.BLACK};
    private int[][] gameGrid;
    private Button[][] tiles;
    private int[][] btnColors;
    public static final int row = 5;
    public static final int col = 5;
    private boolean isBlack;
    private TextView currPlayer;
    private TextView winText;
    private Button btnReset;
    private boolean gameEnded;


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
        gameEnded = false;
    }

    private void reset(){
        gameGrid = new int[row][col];
        setDefaultTileColors();
        createGameGrid();

        isBlack = true;
        currPlayer.setText("Black's Turn");
        currPlayer.setTextColor(colors[1]);
        winText.setText("");
        gameEnded = false;
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

//        System.out.println("Current count: " + leftCount);

        if(leftCount == 3) return true;

        //for horizontal right direction
        int right = c + 1;
        int rightCount = 1;
        //only check twice
        for(int i = 1; i <= 2; i++){
            try{
                int currColor = btnColors[r][right];
                if(currColor == referenceColor){
                    rightCount++;
                }
                right++;
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("Just pass");
                break;
            }
        }

//        System.out.println("Current count: " + rightCount);

        if(rightCount == 3) return true;

        //for vertical top direction
        int top = r - 1;
        int topCount = 1;
        //only check twice
        for(int i = 1; i <= 2; i++){
            try{
                int currColor = btnColors[top][c];
                if(currColor == referenceColor){
                    topCount++;
                }
                top--;
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("Just pass");
                break;
            }
        }

//        System.out.println("Current count: " + topCount);

        if(topCount == 3) return true;

        //for vertical bottom direction
        int bottom = r + 1;
        int bottomCount = 1;
        //only check twice
        for(int i = 1; i <= 2; i++){
            try{
                int currColor = btnColors[bottom][c];
                if(currColor == referenceColor){
                    bottomCount++;
                }
                bottom++;
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("Just pass");
                break;
            }
        }

//        System.out.println("Current count: " + bottomCount);

        if(bottomCount == 3) return true;

        // for the diagonals

        //falling diagonal
        int upperLeftRow = r - 1, upperLeftCol = c - 1;
        int lowerRightRow = r + 1, lowerRightCol = c + 1;
        int diagonal1Count = 1;

        while(upperLeftRow >= 0 || lowerRightRow < row){
            int color1;
            int color2;

            if(upperLeftRow >= 0){
               try{
                   color1 = btnColors[upperLeftRow--][upperLeftCol--];
                   if(color1 == referenceColor) diagonal1Count++;
               }catch(ArrayIndexOutOfBoundsException e){
                   System.out.println("naa sa upper left row");
               }
            }

            if(lowerRightRow < row){
                try{
                    color2 = btnColors[lowerRightRow++][lowerRightCol++];
                    if(color2 == referenceColor) diagonal1Count++;
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("naa sa lower right row");
                }
            }
        }

        if(diagonal1Count >= 3) return true;

        //rising diagonal
        int upperRightRow = r - 1, upperRightCol = c + 1;
        int lowerLeftRow = r + 1, lowerLeftCol = c - 1;
        int diagonal2Count = 1;

        while(upperRightRow >= 0 || lowerLeftRow < row){
            int color1;
            int color2;

            if(upperRightRow >= 0){
                try{
                    color1 = btnColors[upperRightRow--][upperRightCol++];
                    if(color1 == referenceColor) diagonal2Count++;
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("naa sa upper right row");
                }
            }

            if(lowerLeftRow < row){
                try{
                    color2 = btnColors[lowerLeftRow++][lowerLeftCol--];
                    if(color2 == referenceColor) diagonal2Count++;
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("naa sa lower left row");
                }
            }
        }

        if(diagonal2Count >= 3) return true;

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
                    if(gameEnded) return; // do not execute if the game already ended

                    for(int j = row - 1; j >= 0; j--){
                        if(gameGrid[j][currentCol] != -1){
                            continue;
                        }else{
                            gameGrid[j][currentCol] = 1; //set the tile location as taken
                            System.out.println("Row: " + j + " , Column: " + currentCol);

                            boolean gameEnds;

                            if(isBlack){
                                tiles[j][currentCol].setBackgroundColor(colors[1]);
                                btnColors[j][currentCol] = colors[1]; // map the color of the current tile

                                currPlayer.setText("Red's Turn");
                                currPlayer.setTextColor(colors[0]);
                                isBlack = false;

                                gameEnds = check(j, currentCol);

                                if(gameEnds){
                                    winText.setText("BLACK WON!!!");
                                    winText.setTextColor(colors[1]);
                                    currPlayer.setText("HOORAY!");
                                    currPlayer.setTextColor(colors[1]);
                                    gameEnded = true;
                                }

                            }else{
                                tiles[j][currentCol].setBackgroundColor(colors[0]);
                                btnColors[j][currentCol] = colors[0];

                                currPlayer.setText("Black's Turn");
                                currPlayer.setTextColor(colors[1]);
                                isBlack = true;

                                gameEnds = check(j, currentCol);

                                if(gameEnds){
                                    winText.setText("RED WON!!!");
                                    winText.setTextColor(colors[0]);
                                    currPlayer.setText("HOORAY!");
                                    currPlayer.setTextColor(colors[0]);
                                    gameEnded = true;
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