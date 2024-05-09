package com.ozgursahan.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class oyunekran extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    TextView topscoreText;
    int score;
    int topscore;
    int time=15;
    int delay;
    long temp;
    int topdelay;
    String name;
    SharedPreferences sharedPreferences;

    ImageView burak1;
    ImageView burak2;
    ImageView burak3;
    ImageView burak4;
    ImageView burak5;
    ImageView burak6;
    ImageView burak7;
    ImageView burak8;
    ImageView burak9;
    ImageView burak10;
    ImageView burak11;
    ImageView burak12;
    ImageView napim;
    ImageView[] burakArray;
    String control;
    int changingPhoto;

    Handler handler;
    Runnable runnable;
    Button restartButton;
    Button exitButton;
    Button resetButton;

    Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyunekran);

        intent=getIntent();
        delay=intent.getIntExtra("delay",500);
        temp=delay;

        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        topscoreText=findViewById(R.id.topscoreText);

        restartButton=findViewById(R.id.restartButton);
        exitButton=findViewById(R.id.exitButton);
        resetButton=findViewById(R.id.resetButton);

        restartButton.setVisibility(View.INVISIBLE);
        exitButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);

        score=0;

        sharedPreferences=this.getSharedPreferences("com.ozgursahan.reisiyakala", Context.MODE_PRIVATE);

        burak1=findViewById(R.id.burak1);
        burak2=findViewById(R.id.burak2);
        burak3=findViewById(R.id.burak3);
        burak4=findViewById(R.id.burak4);
        burak5=findViewById(R.id.burak5);
        burak6=findViewById(R.id.burak6);
        burak7=findViewById(R.id.burak7);
        burak8=findViewById(R.id.burak8);
        burak9=findViewById(R.id.burak9);
        burak10=findViewById(R.id.burak10);
        burak11=findViewById(R.id.burak11);
        burak12=findViewById(R.id.burak12);

        napim=findViewById(R.id.napim);

        burakArray= new ImageView[] {burak1,burak2,burak3,burak4,burak5,burak6,burak7,burak8,burak9,burak10,burak11,burak12};

        topscore=sharedPreferences.getInt("topscore",0);
        name=sharedPreferences.getString("topname","");
        topdelay=sharedPreferences.getInt("delay",500);

        if(name.matches(""))
        {
            topscoreText.setText("Top Score: "+topscore);
        }
        else
        {
            switch (topdelay)
            {
                case 650: topscoreText.setText(name+"'s Score(Easy): "+topscore); break;
                case 480: topscoreText.setText(name+"'s Score(Medium): "+topscore); break;
                case 390: topscoreText.setText(name+"'s Score(Hard): "+topscore); break;
                default: topscoreText.setText("Top Score: "+topscore);
            }
        }

        hideImages(); // <--- görünmez yapacak fotoları

        new CountDownTimer(time* 1000L, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                timeText.setText("Left: "+l/1000);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                timeText.setText("Time's Up");
                handler.removeCallbacks(runnable); // <-- handler runnable sonlanır

                for(ImageView image : burakArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                if(score > topscore)
                {
                    Toast.makeText(getApplicationContext(),"U got top!",Toast.LENGTH_LONG).show();
                    sharedPreferences.edit().putInt("topscore",score).apply();
                    name=intent.getStringExtra("name");
                    sharedPreferences.edit().putString("topname",name).apply();
                    sharedPreferences.edit().putInt("delay",delay).apply();

                    switch (delay)
                    {
                        case 650: topscoreText.setText(name+"'s Score(Easy): "+score); break;
                        case 480: topscoreText.setText(name+"'s Score(Medium): "+score); break;
                        case 390: topscoreText.setText(name+"'s Score(Hard): "+score); break;
                        default: topscoreText.setText("Top Score: "+score);
                    }

                }

                napim.setVisibility(View.VISIBLE);
                restartButton.setVisibility(View.VISIBLE);
                exitButton.setVisibility(View.VISIBLE);
                resetButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    @SuppressLint("SetTextI18n")
    public void changeScore (View view) {

        /*if (control.equals("reis")) { //foto reisse
            score++;
        } else if (control.equals("czn")) {
            score--;
        }*/
        score++;
        scoreText.setText("Your Score: "+score);
    }

    public void hideImages() {

        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image : burakArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random1=new Random();
                int j=random1.nextInt(12);  // <--- 0-11 arası bir sayı üretir
                Random random=new Random();
                int i=random.nextInt(9);
                if(i>3)
                {
                    //control = "reis";
                    burakArray[j].setVisibility(View.VISIBLE);
                    handler.postDelayed(this,temp);
                }
                else
                {
                    // czn gösterimi
                    //control = "czn";
                    //changingPhoto
                    //burakArray[j].setImageResource(R.drawable.czn);
                    burakArray[j].setVisibility(View.VISIBLE);
                    handler.postDelayed(this,temp*350/500);
                }

                napim.setVisibility(View.INVISIBLE);
            }
        };

        handler.post(runnable);
    }

    public void restart (View view) {
        Intent intent=new Intent(getApplicationContext(),oyunekran.class);
        startActivity(intent);
    }

    public void menu (View view) {
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent2);
    }

    @SuppressLint("SetTextI18n")
    public void reset (View view) {
        sharedPreferences.edit().putInt("topscore",0).apply();
        sharedPreferences.edit().putString("topname","").apply();
        topscoreText.setText("Top Score: 0");
    }

}