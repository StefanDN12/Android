package BBDD;

import viewHolder.TareaViewHolder;

public class EstructuraBBDDTareas {

    EstructuraBBDDTareas(){}

    public static final String TABLE_NAME = "Tareas";
    public static final String COLUMNA1 = "ID";
    public static final String COLUMNA2 = "Nombre";
    public static final String COLUMNA3 = "Minitareas";
    public static final String COLUMNA4 = "IdUsuario";




    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBBDDTareas.TABLE_NAME + " (" +
                    EstructuraBBDDTareas.COLUMNA1 + " INTEGER PRIMARY KEY," +
                    EstructuraBBDDTareas.COLUMNA2 + " TEXT," +
                    EstructuraBBDDTareas.COLUMNA3 + " TEXT,"+
                    EstructuraBBDDTareas.COLUMNA4 + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBBDD.TABLE_NAME;
}
