package com.example.p1_practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.p1_practica1.configuraciones.SQLiteConexion;
import com.example.p1_practica1.configuraciones.Transacciones;
import com.example.p1_practica1.tablas.Empleados;

import java.util.ArrayList;
import java.util.ListIterator;

public class ActivityListView extends AppCompatActivity {
    /* Variables globales */
    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Empleados> listaEmpleados;
    ArrayList <String> ArregloEmpleados;

@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        lista = (ListView) findViewById(R.id.lista);

        ObtenerListaEmpleados();

        //llenar grip con datos empleado
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloEmpleados);
        lista.setAdapter(adp);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ObtenerEmpleado(i);

            }
        });

    }

    private void ObtenerEmpleado( int id) {
        Empleados emple = listaEmpleados.get(id);

        Intent intent = new Intent(getApplicationContext(),Activiry_Edit.class);

        intent.putExtra("codigo", emple.getId()+"");
        intent.putExtra("nombre", emple.getNombres());
        intent.putExtra("apellidos", emple.getApellidos());
        intent.putExtra("edad", emple.getEdad()+"");
        intent.putExtra("correo", emple.getCorreo());

        startActivity(intent);
    }

    private void ObtenerListaEmpleados()
    {
        //conexion a la BD modo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();

        //clase empleados
        Empleados list_emple = null;

        //inicializar array empleados con la clase
        listaEmpleados = new ArrayList<Empleados>();

        //consulta BD directa
        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tblEmpleados, null);

        //RECORRER LA TABLA MOVIENDONOS SOBRE EL CURSOR
        while (cursor.moveToNext())
        {
            list_emple = new Empleados();
            list_emple.setId(cursor.getInt(0));
            list_emple.setNombres(cursor.getString(1));
            list_emple.setApellidos(cursor.getString(2));
            list_emple.setEdad(cursor.getInt(3));
            list_emple.setCorreo(cursor.getString(4));

            listaEmpleados.add(list_emple);
        }
        cursor.close();

        //metodo para llenar lista
        llenarlista();
    }

    private void llenarlista()
    {
        ArregloEmpleados = new ArrayList<String>();

        for (int i=0; i<listaEmpleados.size();i++)
        {
          ArregloEmpleados.add(listaEmpleados.get(i).getId()+"|"+
                  listaEmpleados.get(i).getNombres()+"|"+
                  listaEmpleados.get(i).getApellidos()+"|"+
                  listaEmpleados.get(i).getEdad()+"|"+
                  listaEmpleados.get(i).getCorreo());
        }
    }
}