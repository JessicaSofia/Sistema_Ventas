package com.uce.jessita.sistema_ventas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Jesita on 10/03/2015.
 */
public class Presentacion  extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentacion);

    }
    public void logeoadministrador(View ver){
        Intent admin = new Intent(this,Administrador.class);
        startActivity(admin);
    }
    public void logeocliente(View ver){
        Intent cliente = new Intent(this,Administrador.class);
        startActivity(cliente);
    }
}
