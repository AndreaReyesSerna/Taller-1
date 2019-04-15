package com.example.talleres.ecosistemas.nemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_empezar;
    EditText et_ip;
    static public String IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        this.btn_empezar = findViewById(R.id.btn_empezar);
        this.et_ip = findViewById(R.id.et_ip);


        this.btn_empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String iptext = et_ip.getText().toString();
                if(iptext != ""){
                    IP = iptext;
                    Intent controles = new Intent(MainActivity.this, Personajes.class);
                    startActivity(controles);

                }

            }
        });
    }
}
