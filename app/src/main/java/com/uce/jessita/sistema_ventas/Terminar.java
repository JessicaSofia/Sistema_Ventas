package com.uce.jessita.sistema_ventas;

/**
 * Created by Jesita on 10/03/2015.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class Terminar extends Activity{
    public ListView listaproductos;
    public TextView numeroproductos;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminar);
        listaproductos=(ListView)findViewById(R.id.comprado);
        numeroproductos=(TextView)findViewById(R.id.totalpago);

        //rellenando la lista de compras
        Set<String>set=getAllData();
        final List<String> lista = new ArrayList<String>(set);
        Collections.sort(lista, new Comparator<String>() {

            @Override
            public int compare(String lhs, String rhs) {
                // TODO Auto-generated method stub
                return lhs.compareTo(rhs);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Terminar.this,android.R.layout.simple_list_item_1,lista);
        listaproductos.setAdapter(adapter);
        //fin rellenando lista de compras

        //obtener valor total de la compra
        int uno=suma();
        numeroproductos.setText("Número de artículos: "+uno);
        //fin obtener valor total de la compra
    }

    //obtener productos de la lista
    public Set<String> getAllData(){
        BaseDatos dbobject = new BaseDatos(this,"supermercado2",null,1);
        Set<String> set = new HashSet<String>();
        String Query="select * from compras";
        SQLiteDatabase db = dbobject.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor.moveToFirst()){
            do{
                set.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return set;
    }

    //para suma
    public int suma(){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null,1);
        SQLiteDatabase bd = dbobject.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from compras", null);
        int tamano=cursor.getCount();
        String[] valores=new String[tamano];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                valores[i]=cursor.getString(0);
                System.out.println(valores[i]);
                i++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return tamano;
        //String[] valores2=new String[valores.length];
        //System.out.println(valores[i]);
        //return 0;
    }
    //fin para suma

    //botonterminar
    public void terminarcompra(View ver){
        BaseDatos dbobject = new BaseDatos(this,"supermercado2",null,1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        bd.execSQL("delete from compras");
        Toast.makeText(this,"Compra realizada correctamente", Toast.LENGTH_LONG).show();
        Intent almenu=new Intent(Terminar.this,Inicial.class);
        startActivity(almenu);

    }

}


