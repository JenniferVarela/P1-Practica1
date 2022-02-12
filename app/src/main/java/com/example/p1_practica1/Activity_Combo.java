package com.example.p1_practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.p1_practica1.configuraciones.SQLiteConexion;
import com.example.p1_practica1.configuraciones.Transacciones;
import com.example.p1_practica1.tablas.Empleados;

import java.util.ArrayList;

public class Activity_Combo extends AppCompatActivity {

    SQLiteConexion conexion;

    Spinner spempleados;
    EditText acnombre,acapellido,acedad,accorreo;

    ArrayList<String> lista_empleados;
    ArrayList<Empleados> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        spempleados=(Spinner) findViewById(R.id.spEmpleado);
        acnombre = (EditText) findViewById(R.id.actxtNombre);
        acapellido = (EditText) findViewById(R.id.actxtApellido);
        acedad = (EditText) findViewById(R.id.actxtEdad);
        accorreo = (EditText) findViewById(R.id.actxtCorreo);

        ObtenerListaEmpleados();

        //llenar el combo
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item,lista_empleados);
        spempleados.setAdapter(adp);


        spempleados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                acnombre.setText(lista.get(i).getNombres());
                acapellido.setText(lista.get(i).getApellidos());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ObtenerListaEmpleados() {
        Empleados emple = null;
        lista = new ArrayList<Empleados>();

        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tblEmpleados,null);

        while (cursor.moveToNext())
        {
         emple = new Empleados();

         emple.setId(cursor.getInt(0));
         emple.setNombres(cursor.getString(1));
         emple.setApellidos(cursor.getString(2));
         emple.setEdad(cursor.getInt(3));
         emple.setCorreo(cursor.getString(4));

         lista.add(emple);
        }

        cursor.close();

        fillCombo();
    }

    private void fillCombo() {
        lista_empleados = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++)
        {
         lista_empleados.add(lista.get(i).getId()+" | "+lista.get(i).getNombres()+" | "+lista.get(i).getApellidos());
        }
    }
}