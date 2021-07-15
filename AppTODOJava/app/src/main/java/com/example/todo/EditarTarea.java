package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import BBDD.Helper.BBDDHelperTarea;
import ListaActividades.ListadoActividades;

public class EditarTarea extends AppCompatActivity {


    EditText Titulo;
    EditText Actividades;
    Button btnEditar;


    GlobalVariables tit = new GlobalVariables();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea);


        Titulo = findViewById(R.id.Titl);
       Actividades =  findViewById(R.id.Actv);

        Titulo.setHint(tit.getNombreTarea());
        String remplazo = tit.getMiniTareas().replace(",", " \n");


        Actividades.setHint(remplazo);
        editarTarea();
    }


   public void  editarTarea(){
       btnEditar = findViewById(R.id.buttonEditar);
       final BBDDHelperTarea helper = new BBDDHelperTarea(this);






       SQLiteDatabase db = helper.getReadableDatabase();
       btnEditar.setOnClickListener(v->{
           String textoComa = Actividades.getText().toString().replace("\n", ",");
           Log.d("HOLA", textoComa);

           if(Titulo.getText().toString().equals("")){
               if(textoComa.equals("")){
                   db.execSQL("UPDATE TAREAS SET Nombre = \"" + tit.getNombreTarea() + "\", Minitareas = \""+ tit.getMiniTareas() +"\" WHERE ID = "+ tit.getIdOpcionespuldado());
               }else{
                   db.execSQL("UPDATE TAREAS SET Nombre = \"" + tit.getNombreTarea() + "\", Minitareas = \""+ textoComa +"\" WHERE ID = "+ tit.getIdOpcionespuldado());
               }


           }else{
               if(textoComa.equals("")){

               }
               db.execSQL("UPDATE TAREAS SET Nombre = \"" + Titulo.getText().toString() + "\", Minitareas = \""+ tit.getMiniTareas() +"\" WHERE ID = "+ tit.getIdOpcionespuldado());
           }


           Intent inntensito = new Intent(this, ListadoActividades.class);

           startActivity(inntensito);
       });
    }
}