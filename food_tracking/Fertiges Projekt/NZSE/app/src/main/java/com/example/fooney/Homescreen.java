package com.example.fooney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Homescreen extends AppCompatActivity {
    private Button btnrezepte;
    private Button btnkuehlschrank;
    private Button btnersparnisse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        btnrezepte = (Button) findViewById(R.id.btnrezepte);
        btnrezepte.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openActivity();
            }

        });

        btnkuehlschrank = (Button) findViewById(R.id.btnkuehlschrank);
        btnkuehlschrank.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openActivity2();
            }

        });
/*
        btnersparnisse = (Button) findViewById(R.id.btnersparnisse);
        btnersparnisse.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openActivity3();     //Firasacivitiy
            }

        });
        */
    }

    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
/*
    public void openActivity3(){
        Intent i = new Intent(this,FirstActivity.class);
        startActivity(i);
    }
*/

    public void openActivity2(){
        Intent a = new Intent(this,JJMainActivity.class);
        startActivity(a);
    }
}

