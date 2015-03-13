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
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class Reservaciones2 extends Activity{
    public TextView paratitulo;
    public ListView misublista;
    public String grupo, micodigo,name;
    Bundle extra;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservacion2);
        misublista=(ListView)findViewById(R.id.sublista);
        extra=getIntent().getExtras();
        name=extra.getString("id");

        //para coger el Grupo de Compras1
        paratitulo=(TextView)findViewById(R.id.ProductName);
        Bundle agarre=getIntent().getExtras();
        grupo=agarre.getString("grupo");
        paratitulo.setText("Tipo: "+grupo);
        //fin coger grupo

        //rellenando Sublista
        Set<String> set=getAllData();
        final List<String> sublista= new ArrayList<String>(set);
        Collections.sort(sublista,new Comparator<String>(){
            @Override
            public int compare(String lhs, String rhs) {
                // TODO Auto-generated method stub
                return lhs.compareTo(rhs);
            }
        });

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Reservaciones2.this, android.R.layout.simple_list_item_1,sublista);
        misublista.setAdapter(adapter);

        //guardar en la base de compras
        misublista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int lugar,
                                    long arg3) {
                String uno = misublista.getItemAtPosition(lugar).toString();
                //
                micompra(uno);
                //
            }
        });


    }

    public void micompra(String producto){
        BaseDatos venta= new BaseDatos(this,"supermercado2",null,1);
        SQLiteDatabase bd = venta.getWritableDatabase();
        String prod=producto.toString();
        ContentValues registro = new ContentValues();
        registro.put("producto",prod);
        bd.insert("compras", null, registro);
        bd.close();
        Toast.makeText(this,prod+" agregado a la lista",Toast.LENGTH_LONG).show();
    }

    //obtener datos para la sublista
    public Set<String> getAllData(){
        BaseDatos dbobject=new BaseDatos(this,"supermercado2",null,1);
        SQLiteDatabase db = dbobject.getReadableDatabase();
        Set<String> set = new HashSet<String>();

        //poniendo el nombre del grupo como codigo
        String selectQuery="Select * from productoPadre where nombre ='"+grupo+"'";
        Cursor cursor=db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            micodigo = cursor.getString(0);
        }//fin transformacion

        String subQuery ="select nombre, precio from productoHijo where codpadre='"+micodigo+"'";
        Cursor subcursor=db.rawQuery(subQuery,null);
        if(subcursor.moveToFirst()){
            do{
                set.add(subcursor.getString(0)+" $"+subcursor.getString(1));
            }while(subcursor.moveToNext());
        }
        cursor.close();
        subcursor.close();
        db.close();
        return set;
    }

    public void irmenu(View ver){
        Intent salir = new Intent(this,Reservaciones1.class);
        startActivity(salir);
    }

    public void terminarcompr(View ver){
        String c=name.toString();
        Intent terminar = new Intent(this,Terminar.class);
        terminar.putExtra("id",c);
        startActivity(terminar);
    }

}