package com.uce.jessita.sistema_ventas;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jesita on 12/03/2015.
 */
public class factura extends Activity {
 Bundle extra;
    String name;
    TextView c,n,m,p;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factura);

        extra=getIntent().getExtras();
        name=extra.getString("id");
        c=(TextView) findViewById(R.id.t1);
        n=(TextView) findViewById(R.id.t2);
        m=(TextView) findViewById(R.id.t3);
        p=(TextView) findViewById(R.id.t4);
    }
    public void enviar(View ver){
        BaseDatos dbobject = new BaseDatos(this,"supermercado2",null,1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String c=name.toString();
        String selectQuery1="Select * from cliente where nombre ='"+c+"'";
        Cursor fil=bd.rawQuery(selectQuery1,null);
        if ( fil.moveToFirst()){
            final TextView ci=(TextView)findViewById(R.id.t1);
            final TextView nom=(TextView)findViewById(R.id.t2);
            final TextView co=(TextView)findViewById(R.id.t3);
            final TextView pag=(TextView)findViewById(R.id.t4);
            ci.setText(fil.getString(0));
            nom.setText(fil.getString(1));
            co.setText(fil.getString(2));
            pag.setText("total a pagar $ 24.00");
            Toast.makeText(this, "Compra realizada correctamente", Toast.LENGTH_LONG).show();
            Toast.makeText(this,"Su factura sera enviada al correo", Toast.LENGTH_LONG).show();
        } else{
        bd.close();
    }


    }

}
