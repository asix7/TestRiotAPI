package com.example.andres7.testriotapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RiotAPIRequest request= new RiotAPIRequest();
        DatosUsuario datos_usuario = null;
        try {
            datos_usuario = request.execute("Asix7").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            datos_usuario = null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            datos_usuario = null;
        }

        if(datos_usuario != null) {
            ((TextView) findViewById(R.id.textView)).setText(datos_usuario.getNombreUsuario());
            new DescargarImagen((ImageView) findViewById(R.id.imageView)).execute(datos_usuario.getImagenCampeon());
            new DescargarImagen((ImageView) findViewById(R.id.imageView1)).execute(datos_usuario.getImagenUsuario());
        } else {
            ((TextView) findViewById(R.id.textView)).setText("Usuario no encontrado");
        }





    }
}
