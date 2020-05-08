package com.example.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    private Button onePlayer;
    private Button twoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Setup the buttons
        onePlayer = findViewById(R.id.button_one_player);
        onePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(1);
            }
        });

        twoPlayer = findViewById(R.id.button_two_players);
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(2);
            }
        });

    }

    public void startGame(int players) {
        Intent intent = new Intent(this, MainGame.class);
        intent.putExtra("players", players);
        startActivity(intent);
    }

}
