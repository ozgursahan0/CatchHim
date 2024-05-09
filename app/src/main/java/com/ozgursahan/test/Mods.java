package com.ozgursahan.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mods extends AppCompatActivity {

    Intent intent;
    Intent intent2;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mods);

        intent=getIntent();
        name=intent.getStringExtra("name");

        intent2=new Intent(getApplicationContext(),oyunekran.class);
        intent2.putExtra("name",name);
    }

    public void easy (View view) {
        intent2.putExtra("delay",650);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
    }

    public void medium (View view) {
        intent2.putExtra("delay",480);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
    }

    public void hard (View view) {
        intent2.putExtra("delay",390);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
    }
}