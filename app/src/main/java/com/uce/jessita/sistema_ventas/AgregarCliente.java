package com.uce.jessita.sistema_ventas;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jesita on 10/03/2015.
 */
public class AgregarCliente  extends Activity{
    public EditText etci,etna,etap,etce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearcuenta);

        etci=(EditText)findViewById(R.id.editText1);
        etna=(EditText)findViewById(R.id.editText2);
        etap=(EditText)findViewById(R.id.editText3);
        etce=(EditText)findViewById(R.id.editText4);

    }

    public void fagregar(View v){
        if (etci.getText().length()==10 ){

                BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
                SQLiteDatabase bd = dbobject.getWritableDatabase();
                String ced = etci.getText().toString();
                String nom = etna.getText().toString();
                String email = etap.getText().toString();
                String contr = etce.getText().toString();
                ContentValues registro = new ContentValues();
                registro.put("ciclt",ced);
                registro.put("nombre",nom);
                registro.put("correo",email);
                registro.put("contraseña",contr);
                bd.insert("cliente",null,registro);
                bd.close();
                etci.setText("");
                etna.setText("");
                etap.setText("");
                etce.setText("");
                Toast.makeText(this, "Cliente AGREGADO", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "ERROR Un numero de cedula debe tener 10 digitos", Toast.LENGTH_SHORT).show();
            }

        }


    /*
    public void fconsultar(View v){
        BaseDatos dbobject = new BaseDatos(this, "administracion", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = etci.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,apellido,celular from alumnos where ci=" + ced, null
        );
        if (fila.moveToFirst()){
            etna.setText(fila.getString(0));
            etap.setText(fila.getString(1));
            etce.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe la CEDULA", Toast.LENGTH_LONG).show();
        bd.close();


    }
    public void fmodificar (View v){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = etci.getText().toString();
        String nom = etna.getText().toString();
        String ape = etap.getText().toString();
        String cel = etce.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("ci",ced);
        registro.put("nombre",nom);
        registro.put("apellido",ape);
        registro.put("celular",cel);
        int cant = bd.update("alumnos", registro, "ci=" + ced, null);
        bd.close();
        if(cant==1)
            Toast.makeText(this, "Datos ACTUALIZADOS", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Alumno NO EXISTE", Toast.LENGTH_LONG).show();


    }

    public void fborrar (View v){
        BaseDatos dbobject = new BaseDatos(this, "administracion", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = etci.getText().toString();
        int cant = bd.delete("alumnos", "ci=" + ced, null);
        bd.close();
        etci.setText("");
        etna.setText("");
        etap.setText("");
        etce.setText("");

        if(cant == 1)
            Toast.makeText(this, "Alumno BORRADO", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Alumno NO EXISTE", Toast.LENGTH_LONG).show();
    }
    public void limpiar (View v){

        etci.setText("");
        etna.setText("");
        etap.setText("");
        etce.setText("");
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicial, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


