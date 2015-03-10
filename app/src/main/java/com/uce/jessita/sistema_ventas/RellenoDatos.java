package com.uce.jessita.sistema_ventas;

/**
 * Created by Jesita on 10/03/2015 cambio.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class RellenoDatos extends Activity {
    public Spinner mispinner;
    public EditText idprod, nombreprod,costoprod;
    public String micodigo;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallaingreso);
        mispinner=(Spinner)findViewById(R.id.spinner1);
        idprod=(EditText)findViewById(R.id.editText1);
        nombreprod=(EditText)findViewById(R.id.editText2);
        costoprod=(EditText)findViewById(R.id.editText3);


        //implantacion de datos al spinner
        Set<String>set=getAllData();
        List<String> lista = new ArrayList<String>(set);//datos obtenidos de getAllData como Lista
        //ordena los datos
        Collections.sort(lista, new Comparator<String>() {

            @Override
            public int compare(String lhs, String rhs) {
                // TODO Auto-generated method stub
                return lhs.compareTo(rhs);
            }
        });//fin ordenar datos
        //rellena el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RellenoDatos.this,android.R.layout.simple_spinner_item,lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mispinner.setAdapter(adapter);//fin relleno
        //fin implantacion
    }



    //obtener datos para el spinner
    public Set<String> getAllData(){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        Set<String> set = new HashSet<String>();
        String selectQuery = "select * from productoPadre";//toma los datos de la tabla
        SQLiteDatabase db = dbobject.getReadableDatabase();//datos solo de lectura
        Cursor cursor= db.rawQuery(selectQuery, null);//realiza la consulta
        if(cursor.moveToFirst()){
            do{
                set.add(cursor.getString(1));//aqui se define que campo deseo presentar en el Spinner
            }while(cursor.moveToNext());//fin do
        }//fin if
        cursor.close();
        db.close();
        return set;
    }

    //guardar datos en la base de subproductos
    public void productoagregar(View v){
        BaseDatos dbobject= new BaseDatos(this,"supermercado2",null,1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String id=idprod.getText().toString();
        String prod=nombreprod.getText().toString();
        String costo = costoprod.getText().toString();
        //sacando ID del spinner
        String seleccion = (String) mispinner.getSelectedItem();
        String selectQuery="Select * from productoPadre where nombre ='"+seleccion+"'";
        Cursor cursor=bd.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            micodigo = cursor.getString(0);
        }
        //fin id spinner
        ContentValues registro = new ContentValues();
        registro.put("idsub",id);
        registro.put("nombre", prod);
        registro.put("precio", costo);
        registro.put("codpadre", micodigo);
        bd.insert("productoHijo", null, registro);
        bd.close();
        idprod.setText("");
        nombreprod.setText("");
        costoprod.setText("0.00");
        //
        //String hola=registro.toString();
        Toast.makeText(this,"Producto Agregado",Toast.LENGTH_LONG).show();


    }


    //salir
    public void irmenu(View ver){
        Intent salir = new Intent(this,Inicial.class);
        startActivity(salir);
    }




}