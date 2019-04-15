package com.example.talleres.ecosistemas.nemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Personajes extends AppCompatActivity implements View.OnClickListener {

    Button btn_chiqui, btn_merlin, btn_dori;
    static String PERSONAJE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_personajes);

        this.btn_merlin = findViewById(R.id.merlin);
        this.btn_dori = findViewById(R.id.dori);
        this.btn_chiqui = findViewById(R.id.chiqui);

        this.btn_chiqui.setOnClickListener(this);
        this.btn_dori.setOnClickListener(this);
        this.btn_merlin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chiqui:
                PERSONAJE = "chiqui";
                Intent controles = new Intent(Personajes.this, Controles.class);
                startActivity(controles);
                break;

            case R.id.merlin:
                PERSONAJE = "merlin";
                Intent controles2 = new Intent(Personajes.this, Controles.class);
                startActivity(controles2);
                break;

            case R.id.dori:
                PERSONAJE = "dori";
                Intent controles3 = new Intent(Personajes.this, Controles.class);
                startActivity(controles3);
                break;

        }
    }
}
