package com.ozgursahan.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    String name;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent2=getIntent();

        nameText=findViewById(R.id.nameText);
    }


    public void start (View view) {
        name=nameText.getText().toString();

        if(name.matches(""))
        {
            Toast.makeText(getApplicationContext(),"Enter a name!",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent=new Intent(getApplicationContext(),Mods.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }

    }

    public void exit (View view) {
        //nothing for now
        Toast.makeText(getApplicationContext(),"Exit!",Toast.LENGTH_LONG).show();
    }

}