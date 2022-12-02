package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String tag = "debug";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(tag, "Odpalenie gry");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Log.d(tag, "zakonczenie wczytywania glownego menu");

        Button playButton = findViewById(R.id.button_start_game);
        Intent intent = new Intent(this, GameActivity.class);
        Log.d(tag, "Ustawienie przycisku i intentu");
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tag, "Nacisniecie przycisku");
                startActivity(intent);
                Log.d(tag, "Przechodzenie");
            }
        });
    }
}