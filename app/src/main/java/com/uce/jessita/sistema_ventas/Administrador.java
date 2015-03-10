package com.uce.jessita.sistema_ventas;

/**
 * Created by Jesita on 10/03/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class Administrador extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrador);
    }


    public void loginadmin(View view){
        EditText username = (EditText)findViewById(R.id.editText1);
        EditText password = (EditText)findViewById(R.id.editText2);


        if(username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")){



            Intent ok=new Intent(this,Intermedio.class);
            startActivity(ok);

        }else{
            Toast.makeText(Administrador.this,"Usuario-Password incorrecto",Toast.LENGTH_LONG).show();

        }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicial, menu);
        return true;
    }

}