package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] main_buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int round_count;

    private int player1Points;
    private int player2Points;

    private TextView textView_player1;
    private TextView textView_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_player1 = findViewById(R.id.text_view_p1);
        textView_player2 = findViewById(R.id.text_view_p2);

        // Assign main buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_main_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                main_buttons[i][j] = findViewById(resID);
                main_buttons[i][j].setOnClickListener(this);
            }
        }

        // Assign reset button (WON'T NEED THIS?)
        Button reset_button = findViewById(R.id.button_reset);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Points = 0;
                player2Points = 0;
                round_count = 0;
                textView_player1.setText("Player 1: 0");
                textView_player2.setText("Player 2: 0");
                player1Turn = true;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        main_buttons[i][j].setText("");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button)view).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button)view).setText("X");
        } else {
            ((Button)view).setText("O");
        }

        round_count++;

        if (checkForWin()) {
            if (player1Turn) {
                // Player 1 has won the game
                gameEnd(1);
            } else {
                // Player 2 has won the game
                gameEnd(2);
            }
        } else if (round_count == 9) {
            // The game has been tied
            gameEnd(0);
        }
        player1Turn = !player1Turn;
    }

    /*
        Method to check if the current player has won the game
        Returns true or false based on if the game has been won by a player
     */
    private boolean checkForWin() {
        // Make String array to store values of X's and O's
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = main_buttons[i][j].getText().toString();
            }
        }
        // Check rows to see if winner
        for (int i = 0; i < 3; i++) {
            // Found a winner on the rows
            if (!field[i][0].equals("") && field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])) {
                return true;
            }
        }

        // Check columns to see if winner
        for (int i = 0; i < 3; i++) {
            // Found a winner on the rows
            if (!field[0][i].equals("") && field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])) {
                return true;
            }
        }

        // Check diagonally to see if winner
        if (!field[0][0].equals("") && field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])) {
            return true;
        } else if (!field[0][2].equals("") && field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])) {
            return true;
        } else {
            return false;
        }
    }

    /*
        Method to handle when a player wins the game
     */
    private void gameEnd(int player) {
        // 0 == tie
        // 1 == player1
        // 2 == player2
        if (player == 1) {
            player1Points++;
            Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        } else if (player == 2) {
            player2Points++;
            Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        }

        // Update the points displayed
        textView_player1.setText("Player 1: " + player1Points);
        textView_player2.setText("Player 2: " + player2Points);

        // Reset the game
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                main_buttons[i][j].setText("");
            }
        }
        round_count = 0;
        player1Turn= true;

    }
}
