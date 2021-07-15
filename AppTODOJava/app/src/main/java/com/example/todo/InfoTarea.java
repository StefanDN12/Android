package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import BBDD.Clases.Tareas;
import BBDD.EstructuraBBDDTareas;
import BBDD.Helper.BBDDHelperTarea;
import ListaActividades.ListadoActividades;

public class InfoTarea extends AppCompatActivity {

    private GlobalVariables tit;
    private Tareas tr;
    private Button btnBorrar;
    private Button btnEditar;
    final BBDDHelperTarea helper = new BBDDHelperTarea(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tarea);

        TextView titulo = findViewById(R.id.Titulo);

        TextView tareitas = findViewById(R.id.actividades);

        titulo.setText(tit.getNombreTarea());


        String remplazo = tit.getMiniTareas().replace(",", " \n");

        tareitas.setText(remplazo);
        borrarTodo();
        editar();
    }


    public void borrarTodo(){

        btnBorrar = findViewById(R.id.borrar);
        SQLiteDatabase db = helper.getReadableDatabase();
        btnBorrar.setOnClickListener(v->{
            Log.d("id", String.valueOf(tit.getIdOpcionespuldado()));
           try
           {
                db.execSQL("DELETE FROM TAREAS WHERE ID = "+tit.getIdOpcionespuldado());
                Intent intensito = new Intent(this, ListadoActividades.class);
                startActivity(intensito);
                Log.d("Borrado", "borradito");
           }catch(Exception e){
                e.printStackTrace();
           }

        });
    }

    public void editar(){
        btnEditar = findViewById(R.id.buttonEdi);

        btnEditar.setOnClickListener(v->{
            Intent intensito = new Intent(this, EditarTarea.class);

            startActivity(intensito);
        });
    }
}