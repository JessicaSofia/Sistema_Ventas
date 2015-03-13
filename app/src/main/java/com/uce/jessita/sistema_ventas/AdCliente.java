package com.uce.jessita.sistema_ventas;

/**
  * Created by Jesita on 12/03/2015.
 */
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class AdCliente extends Activity{

    public EditText ci,name,mail,ps;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adcliente);

        ci=(EditText)findViewById(R.id.b1);
        name=(EditText)findViewById(R.id.b2);
        mail =(EditText)findViewById(R.id.b3);
        ps=(EditText)findViewById(R.id.b4);

    }
    public void atr1(View ver){
        Intent c = new Intent(this,Intermedio.class);
        startActivity(c);
    }

    public void fagregar(View v){
        if (ci.getText().length()==10 ){

            BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
            SQLiteDatabase bd = dbobject.getWritableDatabase();
            String ced = ci.getText().toString();
            String nom = name.getText().toString();
            String email = mail.getText().toString();
            String ct = ps.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("ciclt",ced);
            registro.put("nombre",nom);
            registro.put("correo",email);
            registro.put("contraseña",ct);
            bd.insert("cliente",null,registro);
            bd.close();
            ci.setText("");
            name.setText("");
            mail.setText("");
            ps.setText("");
            Toast.makeText(this, "Cliente AGREGADO", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "ERROR Un numero de cedula debe tener 10 digitos", Toast.LENGTH_SHORT).show();
        }

    }

    public void fconsultar(View v){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = ci.getText().toString();
        String selectQuery="Select * from cliente where ciclt ='"+ced+"'";
        Cursor fila = bd.rawQuery(selectQuery, null );
        if (fila.moveToFirst()){
            name.setText(fila.getString(1));
            mail.setText(fila.getString(2));
            ps.setText(fila.getString(3));
        } else{
            Toast.makeText(this, "No existe la CEDULA", Toast.LENGTH_LONG).show();}
        bd.close();
    }
    public void fmodificar (View v){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = ci.getText().toString();
        String nom = name.getText().toString();
        String ape = mail.getText().toString();
        String cel = ps.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("ci",ced);
        registro.put("nombre",nom);
        registro.put("correo",ape);
        registro.put("contraseña",cel);
        int cant = bd.update("cliente", registro, "ciclt=" + ced, null);
        bd.close();
        if(cant==1)
            Toast.makeText(this, "Datos ACTUALIZADOS", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Alumno NO EXISTE", Toast.LENGTH_LONG).show();


    }

    public void fborrar (View v){
        BaseDatos dbobject = new BaseDatos(this, "supermercado2", null, 1);
        SQLiteDatabase bd = dbobject.getWritableDatabase();
        String ced = ci.getText().toString();
        int cant = bd.delete("cliente", "ciclt=" + ced, null);
        bd.close();
        ci.setText("");
        name.setText("");
        mail.setText("");
        ps.setText("");

        if(cant == 1)
            Toast.makeText(this, "cliente BORRADO", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Cliente NO EXISTE", Toast.LENGTH_LONG).show();
    }
    public void limpiar (View v){

        ci.setText("");
        name.setText("");
        mail.setText("");
        ps.setText("");
    }

}

