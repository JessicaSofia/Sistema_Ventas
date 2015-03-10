package com.uce.jessita.sistema_ventas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Jesita on 10/03/2015.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class logeoCliente  extends Activity{
    public EditText user,contraseña;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresocliente);
        user=(EditText)findViewById(R.id.editText1);
        contraseña=(EditText)findViewById(R.id.editText2);


    }
    public void logeocliente(View ver){

        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = user.getText().toString();
        String ps=contraseña.getText().toString();
        Cursor fil = bd.rawQuery(
                "select contraseña from cliente where nombre=" + ced, null
        );
        if (fil==contraseña){
            Intent c = new Intent(this,Reservaciones2.class);
            startActivity(c);
        } else
            Toast.makeText(this, "Error contraseña o Usuario", Toast.LENGTH_LONG).show();
        bd.close();


    }



    public void cuenta(View ver){
        Intent c = new Intent(this,AgregarCliente.class);
        startActivity(c);
    }



    //logeo Cliente
}

