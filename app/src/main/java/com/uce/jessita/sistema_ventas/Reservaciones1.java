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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;



public class Reservaciones1 extends Activity {
    public Reservaciones1() {
        super();
    }

    public ListView milista;
    public TextView probando;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservacion1);
        milista = (ListView) findViewById(R.id.lista);
        probando = (TextView) findViewById(R.id.textView1);

        //rellenando Lista
        Set<String> set = getAllData();
        final List<String> lista = new ArrayList<String>(set);
        Collections.sort(lista, new Comparator<String>() {

            @Override
            public int compare(String lhs, String rhs) {
                // TODO Auto-generated method stub
                return lhs.compareTo(rhs);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Reservaciones1.this, android.R.layout.simple_list_item_1, lista);
        //adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        milista.setAdapter(adapter);

        //PasoDeParametrosParaCargarLosSubProductos
        milista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int lugar,
                                    long arg3) {
                String uno = milista.getItemAtPosition(lugar).toString();
                //evento ir pantalla Compras2
                Intent ir2 = new Intent(Reservaciones1.this, Reservaciones2.class);//InstanciaDeParamaetro
                ir2.putExtra("grupo", uno);//PasandoElDatoNOMBRE
                startActivity(ir2);
            }
        });

    }

    //obtener datos para la lista
    public Set<String> getAllData() {
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        Set<String> set = new HashSet<String>();
        String selectQuery = "select * from productoPadre";
        SQLiteDatabase db = dbobject.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                set.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return set;
    }

    public void irmenu(View ver) {
        Intent salir = new Intent(this, Inicial.class);
        startActivity(salir);
    }

}
