package com.example.banderas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView img_bandera;
    private RadioButton rb1, rb2, rb3;
    private TextView respuesta, puntos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_bandera = findViewById(R.id.iv_bandera);
        rb1 = findViewById(R.id.rb_1);
        rb2 = findViewById(R.id.rb_2);
        rb3 = findViewById(R.id.rb_3);
        respuesta = findViewById(R.id.tv_respuesta);
        puntos = findViewById(R.id.tv_puntos);

        int puntosInt = getIntent().getIntExtra("npuntos", 0);

        if(puntosInt==0){
            puntos.setText("0");
        }else{
            String valor = String.valueOf(puntosInt);
            puntos.setText(valor);
        }


        ArrayList<String> europa_nombre = new ArrayList<String>();

        int europa_bandera [] = {R.drawable.spain, R.drawable.france, R.drawable.germany, R.drawable.denmark, R.drawable.icelang};

        europa_nombre.add("España");
        europa_nombre.add("Francia");
        europa_nombre.add("Alemania");
        europa_nombre.add("Dinamarca");
        europa_nombre.add("Islandia");

        int n_bandera = numeroAleatorio("pais", europa_nombre.size());

        ArrayList<Integer> elegido = new ArrayList<Integer>();
        elegido.add(n_bandera);

        int i = 1;

        while (i<3){
            n_bandera = numeroAleatorio("pais", europa_nombre.size());

            if(!elegido.contains(n_bandera)){
                elegido.add(n_bandera);
                i++;
            }
        }

        img_bandera.setImageResource(europa_bandera[elegido.get(0)]);
        respuesta.setText(europa_nombre.get(elegido.get(0)));


        int posicion_rb = numeroAleatorio("rb",3);

        if(posicion_rb==1){
            rb1.setText(europa_nombre.get(elegido.get(0)));
            rb2.setText(europa_nombre.get(elegido.get(1)));
            rb3.setText(europa_nombre.get(elegido.get(2)));
        } else if (posicion_rb==2) {
            rb1.setText(europa_nombre.get(elegido.get(1)));
            rb2.setText(europa_nombre.get(elegido.get(0)));
            rb3.setText(europa_nombre.get(elegido.get(2)));
        } else if (posicion_rb==3) {
            rb1.setText(europa_nombre.get(elegido.get(2)));
            rb2.setText(europa_nombre.get(elegido.get(1)));
            rb3.setText(europa_nombre.get(elegido.get(0)));
        }
    }

    public static int numeroAleatorio(String tipo, int size){
        int numero = 0;

        if(tipo.equals("rb")){
            numero = (int) (Math.random() * size + 1);
        }else{
            numero = (int) (Math.random() * size);
        }
        return numero;
    }

    public void Comprobar(View view){

        String correcta = respuesta.getText().toString();
        String scoreString = puntos.getText().toString();
        int scoreInt = 0;

        String seleccion = "";

        if(rb1.isChecked() == true){
           seleccion = rb1.getText().toString();
        }
        if(rb2.isChecked() == true){
            seleccion = rb2.getText().toString();
        }
        if(rb3.isChecked() == true){
            seleccion = rb3.getText().toString();
        }


        if(correcta.equals(seleccion)){
            Toast.makeText(this, "Respuesta Correcta", Toast.LENGTH_SHORT).show();
            scoreInt = Integer.parseInt(scoreString);
            scoreInt+=2;
            Intent next = new Intent(this, MainActivity.class);
            next.putExtra("npuntos", scoreInt);
            startActivity(next);
        }else{
            Toast.makeText(this, "NO!!! Prueba otra vez..", Toast.LENGTH_SHORT).show();
        }
    }
}