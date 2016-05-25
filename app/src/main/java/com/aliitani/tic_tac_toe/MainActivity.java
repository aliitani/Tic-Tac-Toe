package com.aliitani.tic_tac_toe;

//Project done by: Ali Itani


import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int player = 0;
    public boolean GameIsOn = true;
    // player 1 = 0, player 2 = 1;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void popIn(View view) {

        ImageView shape = (ImageView) view;

        int tappedShape = Integer.parseInt(shape.getTag().toString());

        if (gameState[tappedShape] == 2 && GameIsOn) {

            if (player == 0) {

                shape.setImageResource(R.drawable.x);

                shape.animate().alpha(1).rotation(360).setDuration(1000);

                gameState[tappedShape] = player;

                player = 1;

            } else {

                shape.setImageResource(R.drawable.o);

                shape.animate().alpha(1).rotation(360).setDuration(300);

                gameState[tappedShape] = player;

                player = 0;

            }
            for (int[] winningPosition: winningPositions) {

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    // somebody won.

                    GameIsOn = false;
                    LinearLayout gameOverLayout = (LinearLayout) findViewById(R.id.GameOverLayout);


                    if (gameOverLayout.getVisibility() == view.INVISIBLE ) {

                        TextView WinningMessage = (TextView) findViewById(R.id.WinnerMessage);

                        if (gameState[winningPosition[0]] == 0) {

                            WinningMessage.setText("X"+" has won!");

                        } else {
                            WinningMessage.setText("O" + " has won!");
                        }

                        gameOverLayout.setVisibility(view.VISIBLE);

                    }

                } else {
                    // its a draw and nobody won..

                    boolean gameIsOver = true;

                    for (int drawCheck :gameState) {

                        if(drawCheck == 2) {

                            gameIsOver = false;

                        }

                    }
                    if (gameIsOver) {

                        TextView WinningMessage = (TextView) findViewById(R.id.WinnerMessage);

                        WinningMessage.setText("It's a draw.");

                        LinearLayout gameOverLayout = (LinearLayout) findViewById(R.id.GameOverLayout);
                        gameOverLayout.setVisibility(view.VISIBLE);

                    }

                }

            }
        }

    }

    public void playAgain(View view) {

        GameIsOn = true;

        LinearLayout gameOverLayout = (LinearLayout) findViewById(R.id.GameOverLayout);

        if (gameOverLayout.getVisibility() == view.VISIBLE) {

            gameOverLayout.setVisibility(view.INVISIBLE);
            player = 0;

            for (int i = 0; i < 9; i++) {

                gameState[i] = 2;

            }

            GridLayout gameTable = (GridLayout) findViewById(R.id.GameTable);

            for (int i =0; i < gameTable.getChildCount(); i ++) {

                ((ImageView) gameTable.getChildAt(i)).setImageResource(0);

            }
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
