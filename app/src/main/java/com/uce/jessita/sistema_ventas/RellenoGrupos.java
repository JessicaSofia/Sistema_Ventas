package com.uce.jessita.sistema_ventas;

/**
 * Created by Jesita on 10/03/2015. cambio
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class RellenoGrupos extends Activity{

    public EditText idgrupo,idnombre;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallagrupo);
        idgrupo=(EditText)findViewById(R.id.b1);
        idnombre=(EditText)findViewById(R.id.b2);
    }

    //ingreso de datos al grupo, tabla productosPadre
    public void grupoagregar(View v){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String id=idgrupo.getText().toString();
        String nombre=idnombre.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("idprod", id);
        registro.put("nombre", nombre);
        bd.insert("productoPadre",null,registro);
        bd.close();
        idgrupo.setText("");
        idnombre.setText("");
        Toast.makeText(this, "Grupo Agregado", Toast.LENGTH_SHORT).show();

    }//fin grupoagregar

    public void irmenu(View ver){
        Intent salir = new Intent(this,Inicial.class);
        startActivity(salir);
    }

}

