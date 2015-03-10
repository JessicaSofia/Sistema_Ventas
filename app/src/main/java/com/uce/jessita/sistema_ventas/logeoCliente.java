package com.uce.jessita.sistema_ventas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jesita on 10/03/2015.
 */
public class logeoCliente  extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresocliente);

    }

    public void cuenta(View ver){
        Intent c = new Intent(this,AgregarCliente.class);
        startActivity(c);
    }
    //logeo Cliente
}

