package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogRecord;

public class GameActivity extends AppCompatActivity {
    Handler handler = new Handler();
    String tag = "debug";
    Random rng = new Random();
    Boolean isGamePlaying;
    int currentLevel = 1, currentScoreInt = 0, bestScoreInt = 0, currentSequence = 0;;
    ArrayList<Integer> sequence = new ArrayList<Integer>();
    TextView bestScore, currentScore;
    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(tag, "odpalenei aktywnosci gry");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        Log.d(tag, "zakonczenie wczytywania gry");

        //deklaracje
        Log.d(tag, " rozpoczecie deklarowania");
        bestScore = (TextView) findViewById(R.id.best_score_number);
        currentScore = (TextView) findViewById(R.id.current_score_number);
        button1 = findViewById(R.id.game_button_1);
        button2 = findViewById(R.id.game_button_2);
        button3 = findViewById(R.id.game_button_3);
        button4 = findViewById(R.id.game_button_4);
        Log.d(tag, "zakonczenie deklaracji");
        Log.d(tag, "ustawianie onClick dla wszystkich przyciskow");
        //event listenery dla przyciskow
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton(2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton(3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton(4);
            }
        });
        Log.d(tag, "zakonczenie ustawianiania onclick dla przyciskow");

        startNewGame();
    }

    public void clickedButton(int buttonCliecked) {
        Log.d(tag, "   wcisnieto przycisk: " + buttonCliecked);
        if(buttonCliecked == sequence.get(currentSequence)) {
            Log.d(tag, "Dobry");
            currentSequence++;
            if (currentSequence >= currentLevel) {
                sequenceEnded();
            }
        }else {
            Log.d(tag, "Zły");
            endGame();
        }
    }
    public void startNewGame() {
        Log.d(tag, "Rozpoczynanie nowej gry");
        sequence.clear();
        currentLevel = 1;
        currentScoreInt = 0;
        currentScore.setText(String.valueOf(currentScoreInt));
        startNewSequence();
    }
    public void endGame() {
        Log.d(tag, "Koniec gry");
        if (bestScoreInt < currentScoreInt) {
            bestScoreInt = currentScoreInt;
            bestScore.setText(String.valueOf(bestScoreInt));
        }
        button1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        button3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        button4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        button1.setTextColor(getResources().getColor(R.color.black));
        button2.setTextColor(getResources().getColor(R.color.black));
        button3.setTextColor(getResources().getColor(R.color.black));
        button4.setTextColor(getResources().getColor(R.color.black));
        button1.setText("X");
        button2.setText("X");
        button3.setText("X");
        button4.setText("X");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                button1.setTextColor(getResources().getColor(R.color.white));
                button1.setText("1");
                button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                button2.setTextColor(getResources().getColor(R.color.white));
                button2.setText("2");
                button3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                button3.setTextColor(getResources().getColor(R.color.white));
                button3.setText("3");
                button4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
                button4.setTextColor(getResources().getColor(R.color.white));
                button4.setText("4");
                startNewGame();
            }
        }, 500);
    }
    public void startNewSequence() {
        Log.d(tag, "Rozpoczynanie nowej sekwencji");
        currentSequence = 0;
        sequence.add(currentLevel - 1,rng.nextInt(4) + 1);
        showSequence();
    }
    public void showSequence() {
        Log.d(tag, "Pokazywanie sekwencji");
        Log.d(tag, "Sekwencja:");
        for (int i = 0; i < currentLevel; i++) {
            Log.d(tag, String.valueOf(sequence.get(i)));
        }
        for (int i = 0; i < currentLevel; i++) {
            int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (sequence.get(finalI)) {
                        case 1:
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_light)));
                                    button1.setTextColor(getResources().getColor(R.color.black));
                                }
                            }, 500 * finalI);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                                    button1.setTextColor(getResources().getColor(R.color.white));
                                }
                            }, 500 * (finalI + 1) - 100);
                            break;
                        case 2:
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue_light)));
                                    button2.setTextColor(getResources().getColor(R.color.black));
                                }
                            }, 500 * finalI);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                                    button2.setTextColor(getResources().getColor(R.color.white));
                                }
                            }, 500 * (finalI + 1) - 100);
                            break;
                        case 3:
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_light)));
                                    button3.setTextColor(getResources().getColor(R.color.black));
                                }
                            }, 500 * finalI);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                                    button3.setTextColor(getResources().getColor(R.color.white));
                                }
                            }, 500 * (finalI + 1) - 100);
                            break;
                        case 4:
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow_light)));
                                    button4.setTextColor(getResources().getColor(R.color.black));
                                }
                            }, 500 * finalI);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    button4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
                                    button4.setTextColor(getResources().getColor(R.color.white));
                                }
                            }, 500 * (finalI + 1) - 100);
                            break;
                    }
                }
            }, 1000);
        }

    }
    public void sequenceEnded() {
        Log.d(tag, "Dobre zakończenie sekwencji");
        currentLevel++;
        currentScoreInt++;
        currentScore.setText(String.valueOf(currentScoreInt));
        button1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        button3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        button4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        button1.setTextColor(getResources().getColor(R.color.black));
        button2.setTextColor(getResources().getColor(R.color.black));
        button3.setTextColor(getResources().getColor(R.color.black));
        button4.setTextColor(getResources().getColor(R.color.black));
        button1.setText("V");
        button2.setText("V");
        button3.setText("V");
        button4.setText("V");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                button1.setTextColor(getResources().getColor(R.color.white));
                button1.setText("1");
                button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                button2.setTextColor(getResources().getColor(R.color.white));
                button2.setText("2");
                button3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                button3.setTextColor(getResources().getColor(R.color.white));
                button3.setText("3");
                button4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
                button4.setTextColor(getResources().getColor(R.color.white));
                button4.setText("4");
                startNewSequence();
            }
        }, 500);
    }
}