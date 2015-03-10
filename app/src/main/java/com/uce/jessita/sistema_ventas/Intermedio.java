package com.uce.jessita.sistema_ventas;

/**
 * Created by Jesita on 10/03/2015. cambio
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class Intermedio extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medio);
    }

    public void opcion1(View ver){
        Intent mi1=new Intent(this,RellenoGrupos.class);
        startActivity(mi1);
    }

    public void opcion2(View ver){
        Intent mi1=new Intent(this,RellenoDatos.class);
        startActivity(mi1);
    }

}
