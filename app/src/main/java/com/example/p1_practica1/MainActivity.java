package com.example.p1_practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnagregar, btnpasar; //variable global
    EditText txtNombres;
    EditText txtApellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnagregar = (Button) findViewById(R.id.btnadd);
        btnpasar = (Button) findViewById(R.id.btnpasar);
        txtNombres = (EditText) findViewById(R.id.txtnombre);
        txtApellidos = (EditText) findViewById(R.id.txtapellido);

        btnagregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(),"Bienvenido "+ txtNombres.getText().toString()+ " "+ txtApellidos.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

        btnpasar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}