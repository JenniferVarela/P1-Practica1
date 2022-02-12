package com.example.p1_practica1.configuraciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class SQLiteConexion  extends SQLiteOpenHelper
{

    public SQLiteConexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) //conexion a la BD
    {
        sqLiteDatabase.execSQL(Transacciones.CreateTableEmpleados);
    }

    @Override
    //Elimina tabla o BD por completo
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL(Transacciones.DropTableEmpleados);
        onCreate(sqLiteDatabase);
    }
}
