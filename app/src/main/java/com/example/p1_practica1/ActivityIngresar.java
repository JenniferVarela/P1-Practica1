package com.example.p1_practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p1_practica1.configuraciones.SQLiteConexion;
import com.example.p1_practica1.configuraciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity
{

    EditText Nombres,Apellidos,Edad,Correo;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        //casteo igualando al objeto nombrado en la interfaz grafica
        Nombres = (EditText) findViewById(R.id.txtnombres);
        Apellidos = (EditText) findViewById(R.id.txtapellidos);
        Edad = (EditText) findViewById(R.id.txtEdad);
        Correo = (EditText) findViewById(R.id.txtCorreo);
        btnadd= (Button) findViewById(R.id.btnagregar);

        btnadd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //procedimeinto proceso de algo
                //Funcion retorna un valor

                AgregarPersonas();

            }
        });

    }

//publica del OnCreate
    private void AgregarPersonas() {
        //conexion a la BD

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1); //instancia de clase
        SQLiteDatabase db = conexion.getWritableDatabase(); //Poner en modo Escritura para hacer el long.

        ContentValues valores= new ContentValues(); //Clase propia de Java para pasar valores
        valores.put(Transacciones.nombres,Nombres.getText().toString());
        valores.put(Transacciones.apellidos,Apellidos.getText().toString());
        valores.put(Transacciones.edad,Edad.getText().toString());
        valores.put(Transacciones.correo,Correo.getText().toString());

        Long resultado= db.insert(Transacciones.tblEmpleados,Transacciones.id,valores);//retorna el resultado que inserto

        Toast.makeText(getApplicationContext(),"Registrado con exito!!! Codigo" + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();


    }

    private void LimpiarPantalla() {
        Nombres.setText("");
        Apellidos.setText("");
        Edad.setText("");
        Correo.setText("");
    }
}