package com.example.p1_practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Activiry_Edit extends AppCompatActivity {

    EditText nombre,apellido,edad,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiry_edit);

        nombre = (EditText) findViewById(R.id.txtEditNom);
        apellido = (EditText) findViewById(R.id.txtEcitApe);
        edad = (EditText) findViewById(R.id.txtEditEdad);
        correo = (EditText) findViewById(R.id.txtEditCorreo);

        String codigos = getIntent().getStringExtra("codigo");
        String nombres = getIntent().getStringExtra("nombre");
        String apellidos = getIntent().getStringExtra("apellidos");
        String edads = getIntent().getStringExtra("edad");
        String correos = getIntent().getStringExtra("correo");

        nombre.setText(nombres);
        apellido.setText(apellidos);
        edad.setText(edads);
        correo.setText(correos);

        System.out.println(codigos);

    }
}