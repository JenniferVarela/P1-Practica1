package com.example.p1_practica1.configuraciones;

public class Transacciones {
    //Nombre BD
    public static final String NameDatabase ="PM01DB";

    //Crear Tablas
    public static String tblEmpleados = "empleados";
    //Campos de tabla
    public static final String id = "id";
    public static final String nombres="nombres";
    public static final String apellidos="apellidos";
    public static final String edad="edad";
    public static final String correo="correo";

    //Transacciones de tablas
    public static final String CreateTableEmpleados = "CREATE TABLE " + tblEmpleados +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT,apellidos TEXT,edad INTEGER,correo TEXT)";

    public static final String DropTableEmpleados = "DROP TABLE IF EXISTS"+ tblEmpleados;


}
