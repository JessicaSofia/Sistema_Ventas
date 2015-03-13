package com.uce.jessita.sistema_ventas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Jesita on 10/03/2015.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class logeoCliente  extends Activity{

    EditText ci,name,mail,ps;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresocliente);
         name=(EditText)findViewById(R.id.l1);
         ps=(EditText)findViewById(R.id.l2);



    }
    public void logeo(View v){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = name.getText().toString();
        String c=ps.getText().toString();
        String selectQuery="Select * from cliente where nombre ='"+ced+"'";
        String selectQuery1="Select * from cliente where contraseña ='"+c+"'";
        Cursor fila = bd.rawQuery(selectQuery, null );
        Cursor fil=bd.rawQuery(selectQuery1,null);
        if (fila.moveToFirst() && fil.moveToFirst()){
            Intent r = new Intent(this,Reservaciones1.class);
            startActivity(r);
        } else{
            Toast.makeText(this, "No existe la CEDULA", Toast.LENGTH_LONG).show();}
        bd.close();
    }

    public void cuenta(View ver){
        Intent c = new Intent(this,AgregarCliente.class);
        startActivity(c);
    }

}

