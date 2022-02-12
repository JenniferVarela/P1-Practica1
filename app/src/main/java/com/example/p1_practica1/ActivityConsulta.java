package com.example.p1_practica1;

import static com.example.p1_practica1.configuraciones.Transacciones.id;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p1_practica1.configuraciones.SQLiteConexion;
import com.example.p1_practica1.configuraciones.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id,nombre,apellidos,edad,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        Button btnconsulta = (Button) findViewById(R.id.btnBuscar);
        id=(EditText) findViewById(R.id.txtCodigoFind);
        nombre=(EditText) findViewById(R.id.txtNom);
        apellidos = (EditText) findViewById(R.id.txtApe);
        edad= (EditText) findViewById(R.id.txtAge);
        correo= (EditText) findViewById(R.id.txtemail);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

    }

    private void Buscar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Parametros de consulta para realizar sentencia select*/
        String[] params = {id.getText().toString()};
        //Campos que encontraremos retornaremos
        String[] fields = {Transacciones.nombres,Transacciones.apellidos,Transacciones.edad,Transacciones.correo};

        String whereCondition = Transacciones.id + "=?";

        try
        {
            /*Crear un cursor para retornar los datos*/
            Cursor cdata = db.query(Transacciones.tblEmpleados, fields,whereCondition,params,null,null,null);
            //mover cursor
            cdata.moveToFirst(); //se mueve al primer elemento que la consulta traiga

            nombre.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            edad.setText(cdata.getString(2));
            correo.setText(cdata.getString(3));

            Toast.makeText(getApplicationContext(),"Consulta Exitosa..!!",Toast.LENGTH_LONG).show();

        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Error al consultar..!!",Toast.LENGTH_LONG).show();
        }

    }
}