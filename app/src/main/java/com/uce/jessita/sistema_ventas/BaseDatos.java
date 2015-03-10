package com.uce.jessita.sistema_ventas;

/**
 * Created by Jesita on 10/03/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BaseDatos extends SQLiteOpenHelper{

    public BaseDatos(Context context, String supermercado2, CursorFactory factory,
                     int version) {
        super(context, supermercado2, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table productoPadre(idprod integer primary key, nombre text)");
        db.execSQL("create table productoHijo(idsub integer primary key, nombre text, precio text, codpadre integer)");
        db.execSQL("create table compras(producto text)");
        db.execSQL("create table clientes(cliente text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists productoPadre");
        db.execSQL("drop table if exists productoHijo");
        db.execSQL("drop table if exists compras");

        db.execSQL("create table productoPadre(idprod integer primary key, nombre text)");
        db.execSQL("create table productoHijo(idsub integer primary key, nombre text, precio text, codpadre integer)");
        db.execSQL("create table compras(producto text)");
    }

    //obtener datos para el spinner
}
