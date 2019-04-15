package com.example.talleres.ecosistemas.nemo;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Controles extends AppCompatActivity implements View.OnClickListener, Observer {

    Button btn_up, btn_down, btn_left, btn_right, btn_boom;
    TextView tv_vidas, tv_puntuacion;
    ControlCliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_controles);

        this.btn_up = findViewById(R.id.btn_up);
        this.btn_down = findViewById(R.id.btn_down);
        this.btn_left = findViewById(R.id.btn_left);
        this.btn_right = findViewById(R.id.btn_right);
        this.btn_boom = findViewById(R.id.btn_boom);

        this.tv_vidas = findViewById(R.id.tv_vidas);
        this.tv_puntuacion = findViewById(R.id.tv_puntuacion);

        this.btn_up.setOnClickListener(this);
        this.btn_down.setOnClickListener(this);
        this.btn_left.setOnClickListener(this);
        this.btn_right.setOnClickListener(this);
        this.btn_boom.setOnClickListener(this);

        String ip = MainActivity.IP;
        String personaje = Personajes.PERSONAJE;

        cliente = ControlCliente.getIntance(ip);
        cliente.setObservador(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_up:

                cliente.enviar("up");
                break;

            case R.id.btn_down:
                cliente.enviar("down");
                break;

            case R.id.btn_left:
                cliente.enviar("left");
                break;

            case R.id.btn_right:
                cliente.enviar("right");
                break;
            case  R.id.btn_boom:
                cliente.enviar("boom");
                break;
        }
    }

    @Override
    public void mensajeRecibido(String mensaje) {

        if(mensaje.contains("vida")){
            String[] m = mensaje.split(":");
            this.tv_vidas.setText(m[1]);
        }

        if(mensaje.contains("puntuacion")){
            String[] m = mensaje.split(":");
            this.tv_puntuacion.setText(m[1]);
        }

    }
}
