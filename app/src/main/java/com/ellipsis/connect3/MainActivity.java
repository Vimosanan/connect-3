package com.ellipsis.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // red - 0, yellow - 1, empty - 2
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        int tagValue = Integer.parseInt(view.getTag().toString());

        if(gameState[tagValue] ==2 && gameActive == true) {
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1500).setDuration(1000);
                gameState[tagValue] = 0;

            } else if (activePlayer == 1) {
                activePlayer = 0;
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1500).setDuration(1000);
                gameState[tagValue] = 1;
            }


            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 0) {
                         winner = "Yellow";
                    } else if (activePlayer == 1) {
                        winner = "Red";

                    }
                    TextView txtWinner = (TextView) findViewById(R.id.txtWinner);
                    Button btnRestart = (Button) findViewById(R.id.btnRestart);

                    txtWinner.setText(winner + " Won!");
                    txtWinner.setVisibility(View.VISIBLE);
                    btnRestart.setVisibility(View.VISIBLE);

                }
            }
        }
    }
    public void playAgainPressed(View view){
        TextView txtWinner = (TextView) findViewById(R.id.txtWinner);
        Button btnRestart = (Button) findViewById(R.id.btnRestart);

        txtWinner.setVisibility(View.GONE);
        btnRestart.setVisibility(View.GONE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i =0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
